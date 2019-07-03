package com.myscrum.model;

import java.io.File;
import java.sql.SQLException;

import com.myscrum.banco.Banco;
import com.mysql.jdbc.PreparedStatement;

public class TratamentoDeAnexo2 {

	public boolean salvarAnexo(File arquivo) {
		byte[] arquivoEmBytes = new byte[(int) arquivo.length()];
		String sql = "INSERT INTO arquivo (nome, arquivo) VALUES (?,?)";
		if (Banco.conexao()) {

			try {
				Banco.st = Banco.con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				Banco.st.setString(1, arquivo.getName().toString());
				Banco.st.setBytes(2, arquivoEmBytes);

				if (Banco.st.executeUpdate() == 1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException erro) {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean excluirAnexo() {
		return false;

	}

	public boolean abrirAnexo() {
		return false;

	}
}
