package net.codejava;

import java.sql.*;
import java.util.*;

public class Banca {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// connessione al database
		String url = "jdbc:mysql://localhost/banca";
		String username = "root";
		String password = "#";

		try {
			System.out.println("Connessione al database avvenuta con successo!");
			while (true) {
				System.out.println("Benvenuto nel software della banca di BitCamp!");
				System.out.println("Seleziona un opzione per procedere: ");
				System.out.println("-----------------------------");
				System.out.println("1) Visualizza dettagli banca: ");
				System.out.println("2) Visualizza dettgli clienti! ");
				System.out.println("3) Prelievi: ");
				System.out.println("4) Depositi: ");
				System.out.println("5) Aggiungere conto corrente!");
				System.out.println("6) Exit!");

				int scelta = input.nextInt();
				input.nextLine();
				System.out.println("\n");

				switch (scelta) {
				case 1:
					Connection conn = DriverManager.getConnection(url, username, password);
					String sqlVisualizzazione = "SELECT * FROM banca";
					// esecuzione della query
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sqlVisualizzazione);
					int i = 0;
					while (rs.next()) {
						i++;
						String nome = rs.getString("nome");
						String sedeBanca = rs.getString("sedeBanca");
						System.out.println("Nome Banca: " + nome + "\nSede Banca: " + sedeBanca);
					}
					rs.close();
					stmt.close();
					conn.close();

					break;
				case 2:
					Connection conn1 = DriverManager.getConnection(url, username, password);
					String sqlVisualizzazione1 = "SELECT * FROM conto_Bancario";
					// esecuzione della query
					Statement stmt1 = conn1.createStatement();
					ResultSet rs1 = stmt1.executeQuery(sqlVisualizzazione1);
					i = 0;
					while (rs1.next()) {
						i++;
						String nomeCliente = rs1.getString("nomeCliente");
						String cognomeCliente = rs1.getString("cognomeCliente");
						double saldo = rs1.getDouble("saldo");
						String numeroContoCorrente = rs1.getString("numeroContoCorrente");
						System.out.println("Nome cliente: " + nomeCliente + "\nCognome cliente: " + cognomeCliente
								+ "\nSaldo: " + saldo + "\nNumeroContocorrente: " + numeroContoCorrente);
					}
					rs1.close();
					stmt1.close();
					conn1.close();
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					System.out.println("Inserimento dei dati del cliente!");
					System.out.print("Inserisci il nome del cliente: ");
					String nomeCliente = input.nextLine();

					System.out.print("Inserisci il cognome del cliente: ");
					String cognomeCliente = input.nextLine();

					System.out.print("Inserisci il saldo del cliente: ");
					double saldo = input.nextDouble();

					Random rand = new Random();
					int generato;
					String numeroContoCorrente = "";
					for (int j = 0; j < 6; j++) {
						numeroContoCorrente += Integer.toString((rand.nextInt(900000) + 100000));
					}
					Connection conn2 = DriverManager.getConnection(url, username, password);
					String sqlInserimento = "INSERT INTO conto_Bancario(nomeCliente, cognomeCliente, saldo, numeroContoCorrente)VALUES(?,?,?,?)";
					// esecuzione della query
					PreparedStatement stmtInserimento = conn2.prepareStatement(sqlInserimento);
					stmtInserimento.setString(1, nomeCliente);
					stmtInserimento.setString(2, cognomeCliente);
					stmtInserimento.setDouble(3, saldo);
					stmtInserimento.setString(4, numeroContoCorrente);
					stmtInserimento.executeUpdate();
					stmtInserimento.close();
					conn2.close();

					break;
				case 6:
					return;
				default:
					System.out.println("Scelta non valida!");
					break;
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante la connessione al database!");
			e.printStackTrace();
		}
	}
}
