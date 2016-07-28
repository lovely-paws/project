package edu.johnshopkins.lovelypaws.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@Scope("session")
public class Cart {

    private Set<Long> ids = new HashSet<>();
    public Set<Long> getIds() { return ids; }
    public void setIds(Set<Long> ids) { this.ids = ids; }

}
