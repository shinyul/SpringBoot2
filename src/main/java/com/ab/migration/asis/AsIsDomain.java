package com.ab.migration.asis;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ab.migration.common.BaseTimeDomain;
import com.ab.migration.tobe.ToBeDomain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//파라미터가 없는 생성자 생성 
@NoArgsConstructor(access = AccessLevel.PUBLIC)
//모든 변수를 포함한 생성자 생성
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
//@Builder(toBuilder = true)
@Builder
@ToString(exclude = "toBeDomain")
@Entity
@Table(name = "AS_IS_DATA")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "asisId")

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class AsIsDomain extends BaseTimeDomain implements Serializable {

	private static final long serialVersionUID = 6385905579792050839L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long asisId;
	
//	@NaturalId
//	@Column(unique = false)	
	@Column(updatable = false)
	private String barcodeNumber;
	@Column(updatable = false)
    private String category;
	@Column(updatable = false)
    private String childGoodsYn;
	@Column(updatable = false)
    private String adultGoodsYn;
	@Column(updatable = false)
    private String importedGoodsDivision;
	@Column(updatable = false)
    private String ProductOrigin;
	@Column(updatable = false)
    private String brandNameAdd_1;
	@Column(updatable = false)
    private String mainBrandName;
	@Column(updatable = false)
    private String brandHoldingCompany;
	@Column(updatable = false)
    private String productNameKr;
	@Column(updatable = false)
    private String productNameEn;
	@Column(updatable = false)
    private String productNameAdd;
	@Column(updatable = false)
    private String merchandiseItems;
	
    @Column(columnDefinition = "TEXT", updatable = false)
    private String handlingPrecautions;
    @Column(columnDefinition = "TEXT", updatable = false)
    private String rawMaterialDescription;
    @Column(columnDefinition = "TEXT", updatable = false)
    private String imageBarcode;
    @Column(columnDefinition = "TEXT", updatable = false)
    private String imageFront;
    @Column(updatable = false)
    private String cookingIngredients;
    @Column(updatable = false)
    private String cookingTime;
    @Column(updatable = false)
    private String cookingQuantity;
    @Column(updatable = false)
    private String characteristic;
    @Column(updatable = false)
    private String minimumAge;
    @Column(updatable = false)
    private String productFunction;
    @Column(updatable = false)
    private String ecoCertification;
    @Column(updatable = false)
    private String typeOfFood;
    @Column(updatable = false)
    private String geneticallyModifiedFoods;
    @Column(updatable = false)
    private String InfantFoodWeightControl;
    @Column(updatable = false)
    private String intakeIntakeMethod;
    @Column(updatable = false)
	private String intakePrecautions;
    @Column(updatable = false)
	private String allergy;
    @Column(updatable = false)
	private String facilityAllergyIngredients;
    @Column(updatable = false)
	private String productCertification;
    @Column(updatable = false)
	private String noAddition;
    @Column(updatable = false)
	private String storageMethod;
    @Column(updatable = false)
	private String intakeMethod;
    @Column(updatable = false)
	private String intakeTarget;
    @Column(updatable = false)
	private String numberOfIntakes;
	@Column(length = 500, updatable = false)
	private String productNutrient_1;
	@Column(length = 500, updatable = false)
	private String productNutrient_2;
	@Setter
	private boolean tobeSaveYn;
	
//  FetchType.EAGER 관계된 Entity의 정보를 미리 읽음 ,  FetchType.LAZY 실제로 요청하는 순간 가져옴. 
//	@OneToOne(optional = true, mappedBy = "asIsDomain" )			//      fetch = FetchType.LAZY 데이터 원할때 가져온다
//	@JoinColumn(name = "asisId" ,referencedColumnName = "asisId", columnDefinition = "asisId", )
//	@OneToOne(optional =true, mappedBy = "asIsDomain", cascade = CascadeType.ALL)
//	@OneToOne( cascade = CascadeType.ALL)
//	mappedBy = "asIsDomain",
	@OneToOne( mappedBy = "asIsDomain", cascade = CascadeType.ALL)
	private ToBeDomain toBeDomain; 
	
	public void setToBeDomain(ToBeDomain details) {
        this.toBeDomain = details;
        this.toBeDomain.setAsIsDomain(this);
    }
	
}
