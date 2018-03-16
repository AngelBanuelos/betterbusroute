/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author angel_banuelos
 */
@Entity
@Table(name = "history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findById", query = "SELECT h FROM History h WHERE h.id = :id"),
    @NamedQuery(name = "History.findByUserId", query = "SELECT h FROM History h WHERE h.userid = :id"),
    @NamedQuery(name = "History.findByLatitude", query = "SELECT h FROM History h WHERE h.latitude = :latitude"),
    @NamedQuery(name = "History.findByLongitude", query = "SELECT h FROM History h WHERE h.longitude = :longitude"),
    @NamedQuery(name = "History.findByTimeStamp", query = "SELECT h FROM History h WHERE h.timeStamp = :timeStamp")})
public class History extends DomainEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private float latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private float longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @JoinColumn(name = "routeid", referencedColumnName = "id")
    @ManyToOne
    private Route routeid;
    @JoinColumn(name = "savedplacedid", referencedColumnName = "id")
    @ManyToOne
    private SavedPlace savedplacedid;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserBbr userid;

    public History() {
    }

    public History(Integer id) {
        this.id = id;
    }

    public History(Integer id, float latitude, float longitude, UserBbr userId) {
        this.userid = userId;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Route getRouteid() {
        return routeid;
    }

    public void setRouteid(Route routeid) {
        this.routeid = routeid;
    }

    public SavedPlace getSavedplacedid() {
        return savedplacedid;
    }

    public void setSavedplacedid(SavedPlace savedplacedid) {
        this.savedplacedid = savedplacedid;
    }

    public UserBbr getUserid() {
        return userid;
    }

    public void setUserid(UserBbr userid) {
        this.userid = userid;
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
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterdegree.ada.finalproject.model.History[ id=" + id + " ]";
    }

}
