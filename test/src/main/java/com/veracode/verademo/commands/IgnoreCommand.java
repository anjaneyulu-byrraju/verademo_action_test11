package com.veracode.verademo.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IgnoreCommand implements BlabberCommand {
	private static final Logger logger = LogManager.getLogger("VeraDemo:IgnoreCommand");

	private Connection connect;

	private String username;

	public IgnoreCommand(Connection connect, String username) {
		super();
		this.connect = connect;
		this.username = username;
	}

	@Override
	public void execute(String blabberUsername) {
		String sqlQuery = "DELETE FROM listeners WHERE blabber=? AND listener=?;";
		logger.info(sqlQuery);
		PreparedStatement action;
		try {
			action = connect.prepareStatement(sqlQuery);

			action.setString(1, blabberUsername);
			action.setString(2, username);
			action.execute();

			sqlQuery = "SELECT blab_name FROM users WHERE username = '" + blabberUsername + "'";
			Statement sqlStatement = connect.createStatement();
			logger.info(sqlQuery);
			ResultSet result = sqlStatement.executeQuery(sqlQuery);
			result.next();

			/* START EXAMPLE VULNERABILITY */
			String event = username + " is now ignoring " + blabberUsername + " (" + result.getString(1) + ")";
			sqlQuery = "INSERT INTO users_history (blabber, event) VALUES (\"" + username + "\", \"" + event + "\")";
			logger.info(sqlQuery);
			sqlStatement.execute(sqlQuery);
			/* END EXAMPLE VULNERABILITY */
		} catch (SQLException e) {
			// TODO Auto-generated catch blocked
			e.printStackTrace();
		}
	}

}

// Modified at 2024-08-12T18:04:20.933Z

// Modified at 2024-08-12T18:11:26.362Z

// Modified at 2024-08-12T18:12:38.266Z

// Modified at 2024-08-12T18:19:30.445Z

// Modified at 2024-08-12T18:22:04.076Z

// Modified at 2024-08-12T18:28:47.376Z

// Modified at 2024-08-12T18:29:42.643Z

// Modified at 2024-08-12T18:29:47.462Z

// Modified at 2024-08-13T05:36:49.701Z

// Modified at 2024-08-13T12:02:12.271Z

// Modified at 2024-08-13T12:17:46.294Z

// Modified at 2024-08-13T12:36:02.498Z

// Modified at 2024-08-13T12:45:07.897Z

// Modified at 2024-08-13T12:53:03.031Z
