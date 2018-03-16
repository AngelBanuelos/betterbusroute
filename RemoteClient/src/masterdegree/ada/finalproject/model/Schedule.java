/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdegree.ada.finalproject.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author angel_banuelos
 */
@Entity
@Table(name = "schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findById", query = "SELECT s FROM Schedule s WHERE s.id = :id"),
    @NamedQuery(name = "Schedule.findByName", query = "SELECT s FROM Schedule s WHERE s.name = :name"),
    @NamedQuery(name = "Schedule.findByMintime", query = "SELECT s FROM Schedule s WHERE s.mintime = :mintime"),
    @NamedQuery(name = "Schedule.findByMaxtime", query = "SELECT s FROM Schedule s WHERE s.maxtime = :maxtime")})
public class Schedule extends DomainEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "calendar")
    private String calendar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mintime")
    private float mintime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maxtime")
    private float maxtime;
    @JoinColumn(name = "routeid", referencedColumnName = "id")
    @ManyToOne
    private Route routeid;

    public Schedule() {
    }

    public Schedule(Integer id) {
        this.id = id;
    }

    public Schedule(Integer id, String name, String calendar, float mintime, float maxtime) {
        this.id = id;
        this.name = name;
        this.calendar = calendar;
        this.mintime = mintime;
        this.maxtime = maxtime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public float getMintime() {
        return mintime;
    }

    public void setMintime(float mintime) {
        this.mintime = mintime;
    }

    public float getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(float maxtime) {
        this.maxtime = maxtime;
    }

    public Route getRouteid() {
        return routeid;
    }

    public void setRouteid(Route routeid) {
        this.routeid = routeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterdegree.ada.finalproject.model.Schedule[ id=" + id + " ]";
    }
    
}
