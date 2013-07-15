// Copyright Samuel Halliday 2012
package com.github.fommil.emokit.jpa;

import com.github.fommil.jpa.CrudDao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

/**
 * @author Sam Halliday
 */
public class EmotivDatumCrud extends CrudDao<UUID, EmotivDatum> {

    public EmotivDatumCrud(EntityManagerFactory emf) {
        super(EmotivDatum.class, emf);
    }
    
    /**
     * 
     * @param EmotivSession es
     * @return List<EmotivDatum>
     */
    public List<EmotivDatum> getDatum(EmotivSession es)
    {
        List<EmotivDatum> led = new ArrayList<EmotivDatum>();
        List<EmotivDatum> oled = this.readAll();
        
        for(EmotivDatum ed : oled)
        {
            if(ed.getSession().getId().equals(es.getId()))
            {
                led.add(ed);
            }
        }
        return led;
    }

}
