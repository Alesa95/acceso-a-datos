package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Serie;
import util.DatabaseConnection;

public class SerieDao extends ObjetoDao implements InterfazDao<Serie> {
	
	private static Connection connection;
	
	public SerieDao () {
		
	}

	@Override
	public ArrayList<Serie> buscarTodos() {
		// TODO Auto-generated method stub
		ArrayList<Serie> series = new ArrayList<>();
		
		connection = openConnection();
		
		String query = "SELECT * FROM series";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Serie serie = new Serie(
							rs.getInt("id"),
							rs.getString("titulo"),
							rs.getInt("edad"),
							rs.getString("plataforma"),
							null
						);
				series.add(serie);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return series;
	}

	@Override
	public Serie buscarPorId(int id) {
		// TODO Auto-generated method stub
		connection = openConnection();
		
		Serie serie = null;
		
		String query = "select * from series where id = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				serie = new Serie(
						rs.getInt("id"),
						rs.getString("titulo"),
						rs.getInt("edad"),
						rs.getString("plataforma"),
						null
				);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		
		return serie;
	}

	@Override
	public void insertar(Serie serie) {
		// TODO Auto-generated method stub
		connection = openConnection();
		
		String query = "insert into series (titulo, edad, plataforma)"
							+ " values (?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, serie.getTitulo());
			ps.setInt(2, serie.getEdad());
			ps.setString(3, serie.getPlataforma());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
	}

	@Override
	public void modificar(Serie t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Serie t) {
		// TODO Auto-generated method stub
		
	}
	
}
