package s;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Identificador {
    public static String 
    pc() {
        String pc = null;
        try {
            pc = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            Logger.getLogger(Identificador.class.getName()).log(Level.SEVERE, null, e);
        }
        return pc;
    }
    public static String 
    usuario() {
        String usuario = System.getProperty("user.name");
        return usuario; 
    }
    public static String 
    ipLocal() {
        String ipLocal = null;
        try {
            ipLocal = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            Logger.getLogger(Identificador.class.getName()).log(Level.SEVERE, null, e);
        }
        return ipLocal; 
    }
    public static String 
    idioma() {
        Locale idioma = Locale.getDefault();
        return idioma.toString();
    }
    public static String
    origen() {
        return Locale.getDefault().getDisplayCountry();
    }
    public static String 
    ipv4(){ 
        String ipv4 = null; 
        Enumeration<NetworkInterface> networkInterface = null; 
        
        try { 
            networkInterface = NetworkInterface.getNetworkInterfaces(); 
        } catch (SocketException e) { 
            throw new RuntimeException(e); 
        } 
        
        while(networkInterface.hasMoreElements()){ 
            NetworkInterface element = networkInterface.nextElement(); 
            Enumeration<InetAddress> InetAddress = element.getInetAddresses(); 
            
            while (InetAddress.hasMoreElements()){ 
                InetAddress ip = InetAddress.nextElement(); 
                if(ip instanceof Inet4Address){ 
                    if(ip.isSiteLocalAddress()){ 
                        ipv4 = ip.getHostAddress(); 
                    } 
                } 
            } 
        } 
        return ipv4; 
    }
    public static String 
    ipv6(){ 
        String ipv6 = null; 
        Enumeration<NetworkInterface> networkInterface = null; 
        
        try { 
            networkInterface = NetworkInterface.getNetworkInterfaces(); 
        } catch (SocketException e) { 
            throw new RuntimeException(e); 
        } 
        
        while(networkInterface.hasMoreElements()){ 
            NetworkInterface element = networkInterface.nextElement(); 
            Enumeration<InetAddress> inetAddress = element.getInetAddresses(); 
            
            while(inetAddress.hasMoreElements()){ 
                InetAddress ip = inetAddress.nextElement(); 
                if (ip instanceof Inet6Address){ 
                    if(ip.isLinkLocalAddress()){ 
                        ipv6 = ip.getHostAddress(); 
                    } 
                } 
            } 
        } 
        return ipv6; 
    }
    public static String 
    macName(){ 
        Enumeration<NetworkInterface> networkInterface = null; 
        
        try { 
            networkInterface = NetworkInterface.getNetworkInterfaces(); 
        } catch (SocketException e) { 
            throw new RuntimeException(e); 
        } 
        
        NetworkInterface current = networkInterface.nextElement();
 
        return current.getDisplayName();
    }
}
