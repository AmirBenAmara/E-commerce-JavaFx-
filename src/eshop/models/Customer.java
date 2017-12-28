/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author Gold Solution
 */
public class Customer {
    private int customerId, ptFidelite,phoneNumber,claimNumber;
    private String customerFirstName,customerLastName,customerMail,customerAddress,customerCIN,customerCP
            ,customerPicturePath,customerUserName,customerPassword,parrain,parrainage;
    private Date customerBirthDate,customerAccountCreation;

    public Customer() {
    }

    public Customer(int customerId, int ptFidelite, String customerFirstName, String customerLastName, String customerMail, String customerAddress, String customerCIN, String customerCP, String customerPicturePath, String customerUserName, String customerPassword, String parrain, String parrainage, Date customerBirthDate, Date customerAccountCreation,int phoneNumber,int claimNumber) {
        this.customerId = customerId;
        this.ptFidelite = ptFidelite;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerMail = customerMail;
        this.customerAddress = customerAddress;
        this.customerCIN = customerCIN;
        this.customerCP = customerCP;
        this.customerPicturePath = customerPicturePath;
        this.customerUserName = customerUserName;
        this.customerPassword = customerPassword;
        this.parrain = parrain;
        this.parrainage = parrainage;
        this.customerBirthDate = customerBirthDate;
        this.customerAccountCreation = customerAccountCreation;
        this.phoneNumber=phoneNumber;
        this.claimNumber=claimNumber;
    }
    
    
    

    public Customer(int ptFidelite, String customerFirstName, String customerLastName, String customerMail, String customerAddress, String customerCIN, String customerCP, String customerPicturePath, String customerUserName, String customerPassword, String parrain, String parrainage, Date customerBirthDate, Date customerAccountCreation,int phoneNumber,int claimNumber) {
        this.ptFidelite = ptFidelite;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerMail = customerMail;
        this.customerAddress = customerAddress;
        this.customerCIN = customerCIN;
        this.customerCP = customerCP;
        this.customerPicturePath = customerPicturePath;
        this.customerUserName = customerUserName;
        this.customerPassword = customerPassword;
        this.parrain = parrain;
        this.parrainage = parrainage;
        this.customerBirthDate = customerBirthDate;
        this.customerAccountCreation = customerAccountCreation;
        this.phoneNumber=phoneNumber;
        this.claimNumber=claimNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(int claimNumber) {
        this.claimNumber = claimNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPtFidelite() {
        return ptFidelite;
    }

    public void setPtFidelite(int ptFidelite) {
        this.ptFidelite = ptFidelite;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCIN() {
        return customerCIN;
    }

    public void setCustomerCIN(String customerCIN) {
        this.customerCIN = customerCIN;
    }

    public String getCustomerCP() {
        return customerCP;
    }

    public void setCustomerCP(String customerCP) {
        this.customerCP = customerCP;
    }

    public String getCustomerPicturePath() {
        return customerPicturePath;
    }

    public void setCustomerPicturePath(String customerPicturePath) {
        this.customerPicturePath = customerPicturePath;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getParrain() {
        return parrain;
    }

    public void setParrain(String parrain) {
        this.parrain = parrain;
    }

    public String getParrainage() {
        return parrainage;
    }

    public void setParrainage(String parrainage) {
        this.parrainage = parrainage;
    }

    public Date getCustomerBirthDate() {
        return customerBirthDate;
    }

    public void setCustomerBirthDate(Date customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
    }

    public Date getCustomerAccountCreation() {
        return customerAccountCreation;
    }

    public void setCustomerAccountCreation(Date customerAccountCreation) {
        this.customerAccountCreation = customerAccountCreation;
    }


    

    @Override
    public String toString() {
        return "Customer{" + "ptFidelite=" + ptFidelite + ", customerFirstName=" + customerFirstName + ", customerLastName=" + customerLastName + ", customerMail=" + customerMail + ", customerAddress=" + customerAddress + ", customerCIN=" + customerCIN + ", customerCP=" + customerCP + ", customerPicturePath=" + customerPicturePath + ", customerUserName=" + customerUserName + ", customerPassword=" + customerPassword + ", parrain=" + parrain + ", parrainage=" + parrainage + ", customerBirthDate=" + customerBirthDate + ", customerAccountCreation=" + customerAccountCreation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.customerId;
        hash = 53 * hash + Objects.hashCode(this.customerMail);
        hash = 53 * hash + Objects.hashCode(this.customerCIN);
        hash = 53 * hash + Objects.hashCode(this.parrain);
        hash = 53 * hash + Objects.hashCode(this.parrainage);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.customerId != other.customerId) {
            return false;
        }
        if (!Objects.equals(this.customerMail, other.customerMail)) {
            return false;
        }
        if (!Objects.equals(this.customerCIN, other.customerCIN)) {
            return false;
        }
        if (!Objects.equals(this.parrainage, other.parrainage)) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        
        return new Customer(this.customerId, this.ptFidelite, this.customerFirstName, this.customerLastName, this.customerMail, this.customerAddress, this.customerCIN, this.customerCP, this.customerPicturePath, this.customerUserName, this.customerPassword, this.parrain, this.parrainage, this.customerBirthDate, this.customerAccountCreation, this.phoneNumber, this.claimNumber);
        
    }
    
    
    
    
    
    
    
    
            
}
