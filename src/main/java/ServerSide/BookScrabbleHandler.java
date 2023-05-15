package ServerSide;

import java.io.*;

public class BookScrabbleHandler implements ClientHandler{

    BufferedReader in;
    PrintWriter out;
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        boolean flag;
        try {
            in=new BufferedReader(new InputStreamReader(inFromclient));
            out=new PrintWriter(outToClient,true);
            String [] arr = in.readLine().split(",");
            String [] bookNames = new String[arr.length-1];
            for(int i=1;i<arr.length ; i++)
            {
                bookNames[i-1] = arr[i];
            }

            if(arr[0] == "Q")
            {
                flag= DictionaryManager.get().query(bookNames);
            }
            else
            {
                flag= DictionaryManager.get().challenge(bookNames);
            }

            out.print(flag);
            out.flush();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        try{
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


