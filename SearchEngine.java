import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler{
    ArrayList<String> totalQueries = new ArrayList<String>(); 

     public String handleRequest(URI url) {
        //if search is add?s
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");

            totalQueries.add(parameters[1]); 
            return String.format("added %s!", parameters[1]);
        }

        //if search is search?
        if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
        
            if(totalQueries.contains(parameters[1])){
                String finalReturn = "";

                for(int i = 0; i < totalQueries.size(); i++){
                    if(totalQueries.get(i).contains(parameters[1])){
                        finalReturn += totalQueries.get(i) + ", ";
                    }
                }
                return String.format("Found %s", finalReturn);
            }
            else{
                return "Not found";
            }
        }
        return "404 Not Found!";
        
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}