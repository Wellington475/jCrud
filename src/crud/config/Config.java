package crud.config;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import crud.database.DataBase;

/**
 * @author Wellington
 */
public class Config {
	private final HashMap<String, String> params;
	
	private String dir;
	
	public Config(){
		params = new HashMap<>();
		params.put("os", System.getProperty("os.name"));
		params.put("home", System.getProperty("user.home"));
		params.put("userDir", System.getProperty("user.dir"));
		params.put("separador", System.getProperty("file.separator"));
		
		setConfigDir();
	}
	
	public final void setConfigDir() {
        dir = params.get("userDir");
    }
	
	public String getSeparador(){
		return params.get("separador");
	}
	
	public String getDir(){
		if(dir==null){
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, "[Error]: Property Dir is not defined.");
		}
		return dir;
	}
	
	public boolean createDir(String path){
		try{
			File folder = new File(path);
			
			if(folder.exists()){
				return true;
			}
			else{
				folder.mkdir();
				return true;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean existsDir(String path){
		File folder = new File(path);
		
		if(folder.exists()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean deleteDir(String path){
		try{
			File folder = new File(path);
			
			if(folder.exists()){
				folder.delete();
				return true;
			}
			else{
				folder.mkdir();
				return false;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean existsConfigDir() {
        File dir = new File(getDir());
        return dir.exists() && dir.isDirectory();
    }

    public String getDatabaseDir() {
        return getDir() + params.get("separador") + "db";
    }
    
    public String getDatabaseFile(){
    	String version = Double.toString(DataBase.version);
		version = version.replaceAll("\\.+","\\_");

    	return getDir() + params.get("separador") + "db" + params.get("separador") + DataBase.dbName +"_" + version + ".sqlite";
    }
    
	public String implode(String separator, String... data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length - 1; i++) {
			if (!data[i].matches(" *")) {
				sb.append(data[i]);
				sb.append(separator);
			}
		}
		sb.append(data[data.length - 1].trim());
		return sb.toString();
	}
}
