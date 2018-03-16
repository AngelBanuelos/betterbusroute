/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author angel_banuelos
 */
@Entity
@Table(name = "saved_place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SavedPlace.findAll", query = "SELECT s FROM SavedPlace s"),
    @NamedQuery(name = "SavedPlace.findById", query = "SELECT s FROM SavedPlace s WHERE s.id = :id"),
    @NamedQuery(name = "SavedPlace.findByUserId", query = "SELECT s FROM SavedPlace s WHERE s.userid = :id"),
    @NamedQuery(name = "SavedPlace.findByName", query = "SELECT s FROM SavedPlace s WHERE s.name = :name AND s.userid = :userId"),
    @NamedQuery(name = "SavedPlace.findByLatitude", query = "SELECT s FROM SavedPlace s WHERE s.latitude = :latitude"),
    @NamedQuery(name = "SavedPlace.findByLongitude", query = "SELECT s FROM SavedPlace s WHERE s.longitude = :longitude"),
    @NamedQuery(name = "SavedPlace.findByRatio", query = "SELECT s FROM SavedPlace s WHERE s.ratio = :ratio")})
public class SavedPlace extends DomainEntity {

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
    @Column(name = "latitude")
    private float latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private float longitude;
    @Column(name = "ratio")
    private Integer ratio;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserBbr userid;
    @OneToMany(mappedBy = "savedplacedid")
    private Collection<History> historyCollection;

    public SavedPlace() {
    }

    public SavedPlace(Integer id) {
        this.id = id;
    }

    public SavedPlace(String name, float latitude, float longitude, int ratio, UserBbr userid) {
        this.ratio = ratio;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userid = userid;
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

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public UserBbr getUserid() {
        return userid;
    }

    public void setUserid(UserBbr userid) {
        this.userid = userid;
    }

    @XmlTransient
    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
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
        if (!(object instanceof SavedPlace)) {
            return false;
        }
        SavedPlace other = (SavedPlace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterdegree.ada.finalproject.model.SavedPlace[ id=" + id + " ]";
    }

}
