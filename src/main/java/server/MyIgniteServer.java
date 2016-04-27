package server;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import models.Person;

public class MyIgniteServer {
	
	public static void main(String[] args) {
		System.setProperty("IGNITE_H2_DEBUG_CONSOLE", "true");
		try (Ignite ignite = Ignition.start(Paths.get("").toAbsolutePath().toString()+"\\config\\example-cache.xml")) {
			
			try (IgniteCache<Integer, Person> cache = ignite.getOrCreateCache("myCache")) { 
				// Load cache on all data nodes with default SQL statement.
                System.out.println(">>> Load ALL data to cache from DB...");
                cache.loadCache(null);

                System.out.println(">>> Loaded cache entries: " + cache.size());
                try {
                    do {
                        System.out.println("Type 'q' and press 'Enter' to stop H2 TCP server...");
                    }
                    while ('q' != System.in.read());
                }
                catch (IOException ignored) {
                    // No-op.
                }
			}
			
		}
		
		
		
	}

}
