package server;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import models.BusinessUnit;

public class IgniteSQLServer {

	public static void main(String[] args) {
try (Ignite ignite = Ignition.start(Paths.get("").toAbsolutePath().toString()+"\\config\\sqlserver-cache.xml")) {
			
			try (IgniteCache<Integer, BusinessUnit> cache = ignite.getOrCreateCache("buCache")) { 
				// Load cache on all data nodes with default SQL statement.
                System.out.println(">>> Load ALL data to cache from DB...");
                cache.loadCache(null);

                System.out.println(">>> Loaded cache entries: " + cache.size());
                BusinessUnit bUnit = cache.get(1);
                System.out.printf("BUID:%d Name:%s CreatedBy:%d",bUnit.buid,bUnit.buName,bUnit.createdByID);
                bUnit.buName = "BU01";
                //cache.put(1, bUnit);
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
