import java.io.*;
import java.net.*;

public class ClientSide 
{	
	public static void main(String[] args) throws Exception
	{	
		System.out.println("Client");
		// declaring needed variables
		String ipAdd = "localhost", pathOfFile = "C:\\ITT_Practice\\secondTextFile.txt";
		int portNum = 9999;
		
		// file which to be sent
		File fileToSend = new File(pathOfFile);
		String fileName = fileToSend.getName(); 
		
		// file to program stream
		FileInputStream fileInputStreamObj = new FileInputStream(fileToSend.getAbsolutePath()); 
		byte[] fileNameInBytes = new byte[(fileName.length())];
		byte[] contentOfFileInBytes = new byte[(int)(fileToSend.length())];
		
		
		// filling byte array with fileName
		fileNameInBytes = fileName.getBytes();	
		
		// read method fill the array with content of file
		fileInputStreamObj.read(contentOfFileInBytes);
		
		
		
		// Socket and sending file
		Socket clientSocket = new Socket(ipAdd, portNum);
		DataOutputStream dataOutputStreamObj = new DataOutputStream(clientSocket.getOutputStream());  
		
		dataOutputStreamObj.writeInt(fileNameInBytes.length); // sending size of title of file to outputStream
		dataOutputStreamObj.write(fileNameInBytes); // sending content of file to outputStream
		dataOutputStreamObj.writeInt(contentOfFileInBytes.length); // sending content length of file to outputStream
		dataOutputStreamObj.write(contentOfFileInBytes); // sending content of file to outputStream
	}
}
