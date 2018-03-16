/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.investigation;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Angel.Sahagun
 */
public class Rutes {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 349; i++) {
            
            String url = "http://rutasgdl.com/mapas/getRouteJSON.php?&rid=" + i;
            URL urlL = new URL(url);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.121.11.33", 8080));
            InputStream result = (InputStream) urlL.openConnection(proxy).getContent();
            Scanner scanner = new Scanner(result);
            System.out.println("");
            while (scanner.hasNext()) {
                System.out.print(scanner.next());
            }
        }
    }

}
