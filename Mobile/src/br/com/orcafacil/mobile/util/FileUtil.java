package br.com.orcafacil.mobile.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class FileUtil {
	
	public final String getFileContent(String file) {
		StringBuilder content = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					this.getClass().getResourceAsStream(file)));
			String str;
			while ((str = reader.readLine()) != null) {
				content.append(str);
			}
			reader.close();
		} catch (IOException e) {
		}

		return content.toString();
	}
}
