import java.io.*;
import java.net.*;

public class ServerSide 
{
	public static void main(String[] args) throws Exception
	{	
		System.out.println("Server");
		// declaring and initializing required variables
		int portNum = 9999, fileNameLen, fileContentLen;
		String pathOfFile = "C:\\Users\\Manish Sharma\\Downloads";
		ServerSocket serverSocketObj = new ServerSocket(portNum);
		Socket serverSideSocket =  serverSocketObj.accept();
		DataInputStream dataInputStreamObj = new DataInputStream(serverSideSocket.getInputStream());
		
		fileNameLen = dataInputStreamObj.readInt();
		if(fileNameLen > 0)
		{
			// dealing with file title
			byte[] fileNameInBytes = new byte[fileNameLen];
			dataInputStreamObj.readFully(fileNameInBytes, 0, fileNameLen); // read data from input stream upto given length
			String fileName = new String(fileNameInBytes);
//			
			
			// dealing with content of file
			fileContentLen = dataInputStreamObj.readInt();
			byte[] fileContentInBytes = new byte[fileContentLen];
			dataInputStreamObj.readFully(fileContentInBytes, 0, fileContentLen);
			
			// creating a file at server end and writing it
			File fileRecieved = new File( "C:\\Users\\Manish Sharma\\Downloads\\" + fileName);
			FileOutputStream fileOutputStreamObj = new FileOutputStream(fileRecieved);
			fileOutputStreamObj.write(fileContentInBytes);
			fileOutputStreamObj.close();
		}
		serverSideSocket.close();
	}
}
