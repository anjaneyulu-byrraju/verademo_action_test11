package com.veracode.verademo.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.*;

public class ListenCommand implements BlabberCommand {
	private static final Logger logger = LogManager.getLogger("VeraDemo:ListenCommand");

	private Connection connect;

	private String username;

	public ListenCommand(Connection connect, String username) {
		super();
		this.connect = connect;
		this.username = username;
	}

	@Override
	public void execute(String blabberUsername) {
		String sqlQuery = "INSERT INTO listeners (blabber, listener, status) values (?, ?, 'Active');";
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
			Set<String> whitelistSqlstatementExecutequerySqlquery = new HashSet<>(Arrays.asList("item1", "item2", "item3"));
			if (!sqlStatement.executeQuery(sqlQuery).matches("\\w+(\\s*\\.\\s*\\w+)*") && !whitelistSqlstatementExecutequerySqlquery.contains(sqlStatement.executeQuery(sqlQuery)))
			    throw new IllegalArgumentException();
			Set<String> whitelistBlabberusername = new HashSet<>(Arrays.asList("item1", "item2", "item3"));
			if (!blabberUsername.matches("\\w+(\\s*\\.\\s*\\w+)*") && !whitelistBlabberusername.contains(blabberUsername))
			    throw new IllegalArgumentException();
			String event = username + " started listening to " + blabberUsername + " (" + sqlStatement.executeQuery(sqlQuery) + ")";
			sqlQuery = "INSERT INTO users_history (blabber, event) VALUES (\"" + username + "\", \"" + event + "\")";
			logger.info(sqlQuery);
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, username);
			statement.execute();
			/* END EXAMPLE VULNERABILITY */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

