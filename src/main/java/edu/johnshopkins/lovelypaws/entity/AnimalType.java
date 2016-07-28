package edu.johnshopkins.lovelypaws.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class AnimalType implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }

    /** The human-readable name for this type of animal (e.g., DOG). */
    @Column(unique = true, nullable = false)
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = StringUtils.upperCase(StringUtils.trimToNull(name)); }

    /** A description for this type of animal. */
    @Column
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = StringUtils.trimToNull(description); }

    public String toString() {
        return String.format("%s#<id=%d>", this.getClass().getSimpleName(), id);
    }
}
