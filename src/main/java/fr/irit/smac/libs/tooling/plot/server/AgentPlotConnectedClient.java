/*
 * #%L
 * agent-tooling
 * %%
 * Copyright (C) 2014 IRIT - SMAC Team
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.libs.tooling.plot.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import fr.irit.smac.libs.tooling.plot.commons.ChartType;

/**
 * Class handling the connection o a new client to the server.
 * 
 * @author Alexandre Perles
 * 
 */
public class AgentPlotConnectedClient implements Runnable {

	private BufferedReader in;
	private AgentPlotServer agentPlotServer;

	public AgentPlotConnectedClient(Socket clientSocket,
			AgentPlotServer _agentPlotServer) {
		try {
			agentPlotServer = _agentPlotServer;
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		String line;
		try {
			while ((line = in.readLine()) != null) {
				String[] res = line.split(";");
				if (res[0].equals("config")) {
					agentPlotServer
							.configChart(res[1], ChartType.valueOf(res[2]));
				} else if (res[0].equals("add")) {
					if (res[2].isEmpty())
						agentPlotServer.getChart(res[1]).add(
								Double.parseDouble(res[3]),
								Double.parseDouble(res[4]));
					else
						agentPlotServer.getChart(res[1]).add(
								res[2],
								Double.parseDouble(res[3]),
								Double.parseDouble(res[4]));
				} else if (res[0].equals("close")) {
					agentPlotServer.getChart(res[1]).close();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
