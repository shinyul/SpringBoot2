package com.ab.migration.tobe;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ab.migration.asis.AsIsDomain;
import com.ab.migration.common.BaseTimeDomain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
//@ToString(exclude = "asIsDomain")
@Builder
@Entity
@Table(name = "TO_BE_DATA")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "tobeId")

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class ToBeDomain extends BaseTimeDomain implements Serializable {

	private static final long serialVersionUID = 2764374626012372284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
	private long tobeId;
	
//	private long asisId;
//	@NaturalId
//	@Column( unique = false )
	private String barcodeNumber;
    private String category;
    private String childGoodsYn;
    private String adultGoodsYn;
    private String importedGoodsDivision;
    private String ProductOrigin;
    private String brandNameAdd_1;
    private String mainBrandName;
    private String brandHoldingCompany;
    private String productNameKr;
    private String productNameEn;
    private String productNameAdd;
    private String merchandiseItems;
    @Column(columnDefinition = "TEXT")
    private String handlingPrecautions;
    @Column(columnDefinition = "TEXT")
    private String rawMaterialDescription;
    @Column(columnDefinition = "TEXT")
    private String imageBarcode;
    @Column(columnDefinition = "TEXT")
    private String imageFront;
    private String cookingIngredients;
    private String cookingTime;
    private String cookingQuantity;
    @Column(columnDefinition = "TEXT")
    private String characteristic;
    private String minimumAge;
    @Column(columnDefinition = "TEXT")
    private String productFunction;
    private String ecoCertification;
    private String typeOfFood;
    private String geneticallyModifiedFoods;
    private String InfantFoodWeightControl;
    private String intakeIntakeMethod;
	private String intakePrecautions;
	private String allergy;
	private String facilityAllergyIngredients;
	private String productCertification;
	private String noAddition;
	@Column(columnDefinition = "TEXT")
	private String storageMethod;
	private String intakeMethod;
	private String intakeTarget;
	private String numberOfIntakes;
	@Column(columnDefinition = "TEXT")
	private String productNutrient_1;
	@Column(columnDefinition = "TEXT")
	private String productNutrient_2;
	
	@Column(columnDefinition = "TEXT")
	private String rawMaterialDescriptionJson;
	@Column(columnDefinition = "TEXT")
	private String characteristicJson;
	@Column(columnDefinition = "TEXT")
	private String productFunctionJson;
	@Column(columnDefinition = "TEXT")
	private String productNutrient_1Json;
	@Column(columnDefinition = "TEXT")
	private String productNutrient_2Json;
	@Column(columnDefinition = "TEXT")
	private String storageMethodJson;
	@Column(columnDefinition = "TEXT")
	private String handlingPrecautionsJson;
	@Column(columnDefinition = "TEXT")
	private String intakePrecautionsJson;
	@Column(columnDefinition = "TEXT")
	private String intakeIntakeMethodJson;
	@Column(length = 500)
	private String salesLinks;

	
//	@OneToOne(optional = true, cascade = CascadeType.MERGE)			
////	@JoinColumn(name = "asisId")
//	@OneToOne(optional = true , mappedBy = "asIsDomain")
//	@JoinColumn(name = "asisId" )
//	@Setter
//	insertable = false, updatable = false
//	@OneToOne(optional = true, cascade = CascadeType.ALL)
//	@OneToOne(optional = true, targetEntity = AsIsDomain.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)//,targetEntity = AsIsDomain.class)
//	@JoinColumn(name="asisId")
//    @JoinColumn(name = "asIsDomain", nullable = false, referencedColumnName = "asisId")
//	@MapsId
//	@PrimaryKeyJoinColumn
	
	
//	@Transient @JsonIgnore
//	private long asisId;
	
    @OneToOne
    @JoinColumn(name = "asisId" , nullable=false,updatable = false)    
    @Setter
	private AsIsDomain asIsDomain;
    
//    public void setAsisIdToAsIsDomain() {
//    	this.asIsDomain = AsIsDomain.builder().asisId(this.asisId).build();
//    }
	
}
