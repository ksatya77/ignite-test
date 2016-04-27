package models;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class BusinessUnit implements Serializable {
	
	public Long id;

    /** Organization ID (indexed). */
    @QuerySqlField(index = true)
    public Integer buid;

    /** First name (not-indexed). */
    @QuerySqlField
    public String buName;
    
    public Integer createdByID;


}
