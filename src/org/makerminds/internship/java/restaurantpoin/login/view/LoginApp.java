package org.makerminds.internship.java.restaurantpoin.login.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.makerminds.internship.java.restaurantpoin.dataProvider.UserDataProvider;
import org.makerminds.internship.java.restaurantpoin.login.controller.LoginController;
import org.makerminds.internship.java.restaurantpoin.login.model.User;
import org.makerminds.internship.java.restaurantpoin.login.model.UserRole;
import org.makerminds.internship.java.restaurantpoin.view.NavigationBar;
import org.makerminds.internship.java.restaurantpoin.view.NavigationBarCook;
import org.makerminds.internship.java.restaurantpoin.view.NavigationBarWaiter;

/**
 * @author Leonora Latifaj
 *
 */
public class LoginApp {
	
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.PLAIN, 15);
	private static JFrame frame;
	private JTextField userNameField = new JTextField(20);
	private JPasswordField passwordField;
	UserDataProvider userdataProvider = new UserDataProvider();
	private String result = "";
	private JLabel resultLabel;
	private static JLayeredPane layeredPane;
	private List<User> userList = userdataProvider.getUselList();
	private static JPanel wellcomePanel;
	private static User allowedUser;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginApp loginView = new LoginApp();
					loginView.prepareView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void prepareView() {
		initializeLoginFrame();
		createLoginComponents();
	}

	private void initializeLoginFrame() {
		frame = new JFrame("Login view");
		frame.setVisible(true);
		frame.setBounds(100, 100, 376, 245);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
	}

	private void createLoginComponents() {
		createUserNameComponent();
		createPasswordComponent();
		createResultComponent();
		createLoginButton();
	}

	private void createResultComponent() {
		resultLabel = new JLabel(result);
		resultLabel.setFont(GENERAL_LABEL_FONT);
		resultLabel.setBounds(23, 171, 327, 24);
		frame.getContentPane().add(resultLabel);
	}

	private void createPasswordComponent() {
		JLabel passwordLabel = createPasswordLabel();
		passwordField = createPasswordTextField();
		frame.getContentPane().add(passwordLabel);
		frame.getContentPane().add(passwordField);
	}

	private JLabel createPasswordLabel() {
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(GENERAL_LABEL_FONT);
		passwordLabel.setBackground(Color.blue);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(0, 79, 120, 40);
		return passwordLabel;
	}

	private JPasswordField createPasswordTextField() {
		passwordField = new JPasswordField();
		passwordField.setFont(GENERAL_LABEL_FONT);
		passwordField.setBounds(109, 79, 230, 30);
		return passwordField;
	}

	private void createUserNameComponent() {
		JLabel userNameLabel = createUserNameJLabel();
		userNameField = createUserNameTextField();
		frame.getContentPane().add(userNameLabel);
		frame.getContentPane().add(userNameField);
	}

	private JLabel createUserNameJLabel() {
		JLabel userNameLabel = new JLabel("Username: ");
		userNameLabel.setFont(GENERAL_LABEL_FONT);
		userNameLabel.setBackground(Color.blue);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setBounds(0, 28, 120, 40);
		return userNameLabel;
	}

	private JTextField createUserNameTextField() {
		userNameField.setColumns(20);
		userNameField.setFont(GENERAL_LABEL_FONT);
		userNameField.setBounds(109, 33, 230, 30);
		return userNameField;
	}

	private void createLoginButton() {
		JButton loginButton = new JButton("Login");
		loginButton.setFont(GENERAL_LABEL_FONT);
		loginButton.setForeground(Color.white);
		loginButton.setBackground(Color.blue);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logInWithProvidedCredencials();
			}
		});
		loginButton.setBounds(130, 130, 107, 30);
		frame.getContentPane().add(loginButton);
	}

	private void logInWithProvidedCredencials() {
		LoginController.getInstance();
		String username = userNameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		boolean credencialProvided = isCredencialProvided(username, password);
		if (password == null || password.isEmpty() || username == null || username.isEmpty()) {
			handelEmptyUserCredencials(password, username);
			resultLabel.setText(result);
		} else {
			if (credencialProvided) {
				LoginController.getInstance().logInUser(username, password);
				result = "Wellcome " + username;
				resultLabel.setText(result);
				frame.dispose();
				createFrame();
			} else {
				handelWrongUserCredencials();
				result = "Wrong input data!!!";
				resultLabel.setText(result);
			}
		}
	}

	private void handelEmptyUserCredencials(String password, String username) {
		if (password == null || password.isEmpty()) {
			result = "Password field is empty!!!";
		} else {
			result = "Username field is empty!!!";
		}
	}

	private void handelWrongUserCredencials() {
		result = "Wrong input data!!!";
		userNameField.setText("");
		passwordField.setText("");
	}

	private boolean isCredencialProvided(String username, String password) {
		for (User user : userList) {
			if (user.getName().equals(username) && user.getPassword().equals(password)) {
				allowedUser = user;
				return true;
			}
		}
		return false;
	}

	public static void createFrame() {
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(200, 0, 800, 460);
		layeredPane.add(createWellcomePanel());
		frame = new JFrame("Restaurant point");
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 1000, 510);
		frame.setBackground(Color.CYAN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		if(allowedUser.getUserRole() == UserRole.MANAGER) {
			JPanel navigationBarPanel = NavigationBar.createNavigationBarPanel();
			frame.getContentPane().add(navigationBarPanel);
			frame.getContentPane().add(layeredPane);
		}
		else if(allowedUser.getUserRole() == UserRole.WAITER) {
			JPanel navigationBarPanel = NavigationBarWaiter.createNavigationBarPanel();
			frame.getContentPane().add(navigationBarPanel);
			layeredPane.add(createWellcomePanel());
			frame.getContentPane().add(layeredPane);
		}
		else {
			JPanel navigationBarPanel = NavigationBarCook.createNavigationBarPanel(allowedUser.getRestaurant());
			frame.getContentPane().add(navigationBarPanel);
			layeredPane.add(createWellcomePanel());
			frame.getContentPane().add(layeredPane);
		}
	}
	public static void changePanels(JPanel panel) {
		layeredPane.removeAll();
		//layeredPane.setBackground(Color.gray);
		layeredPane.repaint();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	public static void signOut() {
			frame.dispose();
			LoginApp loginView = new LoginApp();
			loginView.prepareView();
		}
	public static JPanel createWellcomePanel() {
		wellcomePanel = new JPanel();
		wellcomePanel.setLayout(null);
		wellcomePanel.setBounds(0, 0, 800, 470);
		//wellcomePanel.setBackground(Color.white);
		JLabel label = new JLabel("<html>User role "+allowedUser.getUserRole()+"<br><br> Signed in successfully. <br> Wellcome at "+allowedUser.getRestaurant()+".</html>");
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.BLACK);
		label.setBounds(100,100,500,300);
		wellcomePanel.add(label);
		return wellcomePanel;
	}
}
