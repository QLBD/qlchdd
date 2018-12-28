package model.entities;
// Generated Dec 11, 2018 1:37:51 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Baohanh generated by hbm2java
 */
@Entity
@Table(name = "baohanh", catalog = "qlchdd")
public class BaoHanh implements java.io.Serializable {

	private int maBh;
	private HoaDonBan hoadonban;
        private SanPham sanpham;
	private int serial;
	private String yeucauBh;
	private Date ngaynhan;
	private int tinhtrang;
	private Date ngaytra;
	

	public BaoHanh() {
	}

        public BaoHanh(int maBh, HoaDonBan hoadonban, SanPham sanpham, int serial, String yeucauBh, Date ngaynhan, int tinhtrang) {
            this.maBh = maBh;
            this.hoadonban = hoadonban;
            this.sanpham = sanpham;
            this.serial = serial;
            this.yeucauBh = yeucauBh;
            this.ngaynhan = ngaynhan;
            this.tinhtrang = tinhtrang;
        }

	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_BH", unique = true, nullable = false)
	public int getMaBh() {
		return this.maBh;
	}

	public void setMaBh(int maBh) {
		this.maBh = maBh;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sohd_Ban", nullable = false)
	public HoaDonBan getHoadonban() {
		return this.hoadonban;
	}

	public void setHoadonban(HoaDonBan hoadonban) {
		this.hoadonban = hoadonban;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_SP", nullable = false)
	public SanPham getSanpham() {
		return this.sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

	@Column(name = "serial", nullable = false)
	public int getSerial() {
		return this.serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	@Column(name = "yeucau_BH", nullable = false, length = 45)
	public String getYeucauBh() {
		return this.yeucauBh;
	}

	public void setYeucauBh(String yeucauBh) {
		this.yeucauBh = yeucauBh;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaynhan", nullable = false, length = 19)
	public Date getNgaynhan() {
		return this.ngaynhan;
	}

	public void setNgaynhan(Date ngaynhan) {
		this.ngaynhan = ngaynhan;
	}

	@Column(name = "tinhtrang", nullable = false)
	public int getTinhtrang() {
		return this.tinhtrang;
	}

	public void setTinhtrang(int tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaytra", nullable = false, length = 19)
	public Date getNgaytra() {
		return this.ngaytra;
	}

	public void setNgaytra(Date ngaytra) {
		this.ngaytra = ngaytra;
	}
}
