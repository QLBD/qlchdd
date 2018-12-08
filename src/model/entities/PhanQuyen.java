package model.entities;
// Generated Dec 7, 2018 9:42:19 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Phanquyen generated by hbm2java
 */
@Entity
@Table(name = "phanquyen", catalog = "qlchdd")
public class PhanQuyen implements java.io.Serializable {

	private int maPhanQuyen;
	private String quyentruycap;
	private Set<TaiKhoan> taikhoans = new HashSet<TaiKhoan>(0);

	public PhanQuyen() {
	}

	public PhanQuyen(int maPhanQuyen) {
		this.maPhanQuyen = maPhanQuyen;
	}

	public PhanQuyen(int maPhanQuyen, String quyentruycap, Set<TaiKhoan> taikhoans) {
		this.maPhanQuyen = maPhanQuyen;
		this.quyentruycap = quyentruycap;
		this.taikhoans = taikhoans;
	}

	@Id

	@Column(name = "ma_PhanQuyen", unique = true, nullable = false)
	public int getMaPhanQuyen() {
		return this.maPhanQuyen;
	}

	public void setMaPhanQuyen(int maPhanQuyen) {
		this.maPhanQuyen = maPhanQuyen;
	}

	@Column(name = "quyentruycap", length = 45)
	public String getQuyentruycap() {
		return this.quyentruycap;
	}

	public void setQuyentruycap(String quyentruycap) {
		this.quyentruycap = quyentruycap;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phanquyen")
	public Set<TaiKhoan> getTaikhoans() {
		return this.taikhoans;
	}

	public void setTaikhoans(Set<TaiKhoan> taikhoans) {
		this.taikhoans = taikhoans;
	}

}
