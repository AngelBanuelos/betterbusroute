/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.model;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user_bbr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserBbr.findAll", query = "SELECT u FROM UserBbr u"),
    @NamedQuery(name = "UserBbr.findById", query = "SELECT u FROM UserBbr u WHERE u.id = :id"),
    @NamedQuery(name = "UserBbr.findByFirstname", query = "SELECT u FROM UserBbr u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "UserBbr.findByLastname", query = "SELECT u FROM UserBbr u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "UserBbr.findByEmail", query = "SELECT u FROM UserBbr u WHERE u.email = :email"),
    @NamedQuery(name = "UserBbr.findByPassword", query = "SELECT u FROM UserBbr u WHERE u.password = :password"),
    @NamedQuery(name = "UserBbr.findByLatitude", query = "SELECT u FROM UserBbr u WHERE u.latitude = :latitude"),
    @NamedQuery(name = "UserBbr.findByLongitude", query = "SELECT u FROM UserBbr u WHERE u.longitude = :longitude"),
    @NamedQuery(name = "UserBbr.findByEnable", query = "SELECT u FROM UserBbr u WHERE u.enable = :enable")})
public class UserBbr extends DomainEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "password")
    private String password;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Float latitude;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "enable")
    private Boolean enable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Alert> alertCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<SavedPlace> savedPlaceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<History> historyCollection;

    public UserBbr() {
    }

    public UserBbr(Integer id) {
        this.id = id;
    }

    public UserBbr(Integer id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @XmlTransient
    public Collection<Alert> getAlertCollection() {
        return alertCollection;
    }

    public void setAlertCollection(Collection<Alert> alertCollection) {
        this.alertCollection = alertCollection;
    }

    @XmlTransient
    public Collection<SavedPlace> getSavedPlaceCollection() {
        return savedPlaceCollection;
    }

    public void setSavedPlaceCollection(Collection<SavedPlace> savedPlaceCollection) {
        this.savedPlaceCollection = savedPlaceCollection;
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
        if (!(object instanceof UserBbr)) {
            return false;
        }
        UserBbr other = (UserBbr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterdegree.ada.finalproject.model.UserBbr[ id=" + id + " ]";
    }

}
