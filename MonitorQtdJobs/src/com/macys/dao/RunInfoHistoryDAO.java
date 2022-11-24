package com.macys.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.macys.factory.ConnectionFactory;

public class RunInfoHistoryDAO {

	public int showQuantityOfJobsIn24Hours(String orderDate) throws ClassNotFoundException, IOException, InterruptedException{
				
		ConnectionFactory cf = new ConnectionFactory();
		
		int x=0;

		try (Connection conexao = cf.conectar();) {
			
			//System.out.println("Sucesso!");
			String sqlGet = "select count(order_date)  from runinfo_history where order_date like ?";
			PreparedStatement pstm = conexao.prepareStatement(sqlGet);
			pstm.setString(1, orderDate);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				x = rs.getInt("count");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return x;
		
	}
	
}