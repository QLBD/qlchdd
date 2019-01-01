package model.entities;
// Generated Dec 11, 2018 1:37:51 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CthdMua generated by hbm2java
 */
@Entity
@Table(name = "cthd_mua", catalog = "qlchdd")
public class CthdMua implements java.io.Serializable {

	private CthdMuaId id;
	private HoaDonMua hoadonmua;
	private SanPham sanpham;
	private Integer sl;
        private Double dongiaSp;
	private Double thanhtien;

	public CthdMua() {
	}

        public CthdMua(CthdMuaId id, HoaDonMua hoadonmua, SanPham sanpham, Integer sl, Double dongiaSp) {
            this.id = id;
            this.hoadonmua = hoadonmua;
            this.sanpham = sanpham;
            this.sl = sl;
            this.dongiaSp = dongiaSp;
            this.thanhtien = 0.0D;
        }

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "sohdMua", column = @Column(name = "sohd_Mua", nullable = false)),
			@AttributeOverride(name = "maSp", column = @Column(name = "ma_SP", nullable = false)) })
	public CthdMuaId getId() {
		return this.id;
	}

	public void setId(CthdMuaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sohd_Mua", nullable = false, insertable = false, updatable = false)
	public HoaDonMua getHoadonmua() {
		return this.hoadonmua;
	}

	public void setHoadonmua(HoaDonMua hoadonmua) {
		this.hoadonmua = hoadonmua;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_SP", nullable = false, insertable = false, updatable = false)
	public SanPham getSanpham() {
		return this.sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

	@Column(name = "sl")
	public Integer getSl() {
		return this.sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}

        @Column(name = "dongia_SP", nullable = false, precision = 22, scale = 0)
	public double getDongiaSp() {
            return this.dongiaSp;
	}

	public void setDongiaSp(Double dongiaSp) {
		this.dongiaSp = dongiaSp;
	}
        
	@Column(name = "thanhtien", precision = 22, scale = 0, columnDefinition = "double default 0")
	public Double getThanhtien() {
		return this.thanhtien;
	}

	public void setThanhtien(Double thanhtien) {
		this.thanhtien = thanhtien;
	}

}
