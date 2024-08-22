package com.trintel.equipment.admin.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MAINTENANCE_HISTORY")
public class MaintenanceHistoryEO{
    @Id
    @Column(nullable = false, length = 50)
    private String id;
    @Column(name="SERVICE_COMPLETION_DATE")
    private Timestamp serviceCompletionDate;
    @Column(name="SERVICE_START_DATE")
    private Timestamp serviceStartDate;
    @Column(name="TECHNICIAN_COMMENTS", length = 200)
    private String technicianComments;
    @Column(name="TECHNICIAN_NAME", length = 50)
    private String technicianName;
    @ManyToOne
    @JoinColumn(name = "EQUIPMENT_ID")
    private EquipmentEO equipment;
    @ManyToOne
    @JoinColumn(name = "BILL_NUMBER")
    private BillEO bills;
	@Column(name="IS_ACTIVE")
	private Character isActive;

    public MaintenanceHistoryEO() {
    }

    public MaintenanceHistoryEO(BillEO bills, EquipmentEO equipment, String id,
                              Timestamp serviceCompletionDate,
                              Timestamp serviceStartDate,
                              String technicianComments,
                              String technicianName, Character isActive) {
        this.bills = bills;
        this.equipment = equipment;
        this.id = id;
        this.serviceCompletionDate = serviceCompletionDate;
        this.serviceStartDate = serviceStartDate;
        this.technicianComments = technicianComments;
        this.technicianName = technicianName;
        this.isActive = isActive;
    }


    public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getServiceCompletionDate() {
        return serviceCompletionDate;
    }

    public void setServiceCompletionDate(Timestamp serviceCompletionDate) {
        this.serviceCompletionDate = serviceCompletionDate;
    }

    public Timestamp getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Timestamp serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public String getTechnicianComments() {
        return technicianComments;
    }

    public void setTechnicianComments(String technicianComments) {
        this.technicianComments = technicianComments;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public EquipmentEO getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEO equipment) {
        this.equipment = equipment;
    }

    public BillEO getBills() {
        return bills;
    }

    public void setBills(BillEO bills) {
        this.bills = bills;
    }
}
