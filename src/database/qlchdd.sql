DROP DATABASE IF EXISTS qlchdd;

--
-- Database: `qlchdd`
--
CREATE SCHEMA IF NOT EXISTS `qlchdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `qlchdd` ;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Nam` ()  NO SQL
SELECT doanhthu.nam, SUM(doanhthu.tienban_SP) AS tienban_SP, SUM(doanhthu.tienmua_SP) AS tienmua_SP, SUM(doanhthu.tienluong_NV) AS tienluong_NV, SUM(doanhthu.tienloi) AS tienloi
FROM doanhthu
GROUP BY doanhthu.nam$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Quy` (IN `nam` INT)  NO SQL
SELECT doanhthu.quy, doanhthu.nam, SUM(doanhthu.tienban_SP) AS tienban_SP, SUM(doanhthu.tienmua_SP) AS tienmua_SP, SUM(doanhthu.tienluong_NV) AS tienluong_NV, SUM(doanhthu.tienloi) AS tienloi
FROM doanhthu
WHERE doanhthu.nam = nam
GROUP BY doanhthu.quy$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Thang` (IN `nam` INT)  NO SQL
SELECT * FROM doanhthu WHERE doanhthu.nam = nam
ORDER BY doanhthu.thang$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HDBan_NgBan` (IN `NGBAN` DATE)  NO SQL
SELECT * FROM hoadonban WHERE hoadonban.ngay_Ban = NGBAN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HDMua_NgNhap` (IN `NGNHAP` DATE)  NO SQL
SELECT * FROM hoadonmua WHERE hoadonmua.ngay_Nhap = NGNHAP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `KhachHang` (IN `TenKH` VARCHAR(45))  NO SQL
SELECT * FROM khachhang WHERE ten_KH LIKE CONCAT('%',TENKH,'%')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `luong_nhanvien` (IN `thang` INT, IN `nam` INT)  NO SQL
SELECT nhanvien.ma_NV, nhanvien.ten_NV,nhanvien.luong_CB, hoahong.tien_HH,(nhanvien.luong_CB+hoahong.tien_HH) AS luong
FROM nhanvien, hoahong
WHERE nhanvien.ma_NV = hoahong.ma_NV AND nhanvien.tinh_trang = 1 AND hoahong.thang = thang AND hoahong.nam = nam
GROUP BY nhanvien.ma_NV, nhanvien.ten_NV$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `NhanVien` (IN `TENNV` VARCHAR(45))  NO SQL
SELECT * FROM nhanvien WHERE ten_NV LIKE CONCAT('%',TENNV,'%')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PhieuNhanBaoHanh` (IN `maBH` INT)  NO SQL
SELECT baohanh.ma_BH, baohanh.sohd_Ban, baohanh.ma_SP, baohanh.serial, baohanh.yeucau_BH, baohanh.ngaynhan, khachhang.ma_KH, khachhang.ten_KH, khachhang.soDT_KH, khachhang.diachi_KH, sanpham.ten_SP, sanpham.thoigian_BH, hoadonban.ngay_Ban, nhanvien.ma_NV, nhanvien.ten_NV
FROM baohanh, khachhang, hoadonban, sanpham, nhanvien
WHERE baohanh.sohd_Ban = hoadonban.sohd_Ban AND hoadonban.ma_KH = khachhang.ma_KH AND sanpham.ma_SP = baohanh.ma_SP AND baohanh.nhanvien_Nhan = nhanvien.ma_NV AND baohanh.ma_BH = maBH$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PhieuTraBaoHanh` (IN `maBH` INT)  NO SQL
SELECT baohanh.ma_BH, baohanh.sohd_Ban, baohanh.ma_SP, baohanh.serial, baohanh.yeucau_BH, baohanh.ngaynhan, baohanh.ngaytra, khachhang.ma_KH, khachhang.ten_KH, khachhang.soDT_KH, khachhang.diachi_KH, sanpham.ten_SP, sanpham.thoigian_BH, hoadonban.ngay_Ban, nhanvien.ma_NV, nhanvien.ten_NV
FROM baohanh, khachhang, hoadonban, sanpham, nhanvien
WHERE baohanh.sohd_Ban = hoadonban.sohd_Ban AND hoadonban.ma_KH = khachhang.ma_KH AND sanpham.ma_SP = baohanh.ma_SP AND baohanh.nhanvien_Tra = nhanvien.ma_NV AND baohanh.ma_BH = maBH$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SanPham` (IN `TENSP` VARCHAR(45))  NO SQL
SELECT * FROM sanpham WHERE ten_SP LIKE CONCAT('%',TENSP,'%')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_NhanVien_DoanhThu` ()  NO SQL
SELECT nv.ma_NV, nv.ten_NV,Month(hdb.ngay_Ban) Thang, Year(hdb.ngay_Ban)Nam, SUM(hdb.tongtien_Ban) TongTienBan
	FROM nhanvien nv
    LEFT JOIN hoadonban hdb on hdb.ma_NV = nv.ma_NV
  
	GROUP BY nv.ma_NV, Thang, Nam
    ORDER BY nv.ma_NV$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongNam` (IN `nam` INT)  NO SQL
SELECT sp.ma_SP, sp.ten_SP, SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam
GROUP BY sp.ma_SP, sp.ten_SP
ORDER BY SL DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongQuy` (IN `quy` INT, IN `nam` INT)  NO SQL
SELECT sp.ma_SP , sp.ten_SP , SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam and quarter(hd.ngay_Ban) = quy
GROUP BY sp.ma_SP, sp.ten_SP
ORDER BY SL DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongThang` (IN `thang` INT, IN `nam` INT)  NO SQL
SELECT sp.ma_SP , sp.ten_SP , SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam and month(hd.ngay_Ban) = thang
GROUP BY sp.ma_SP, sp.ten_SP
ORDER BY SL DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongThang-Nam` (IN `th` INT(11), IN `n` INT(11))  NO SQL
SELECT sp.ma_SP, sp.ten_SP, sum(db.sl) sl
FROM sanpham sp LEFT JOIN (SELECT ct.ma_SP FROM cthd_ban ct, hoadonban hd WHERE ct.sohd_Ban = hd.sohd_Ban and Month(hd.ngay_Ban) = th and year(hd.ngay_Ban) = n) as db ON sp.ma_SP = db.ma_SP


GROUP BY sp.ma_SP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_TOP5SP_BanChay_Nam` ()  NO SQL
SELECT ct.ma_SP, sp.ten_SP, SUM(ct.sl) SLBanRa
FROM cthd_ban ct, sanpham sp
WHERE sp.ma_SP = ct.ma_SP

GROUP BY sp.ma_SP
ORDER BY SLBanRa DESC
LIMIT 5$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_Login` (IN `tenDangNhap` VARCHAR(45), IN `matKhau` VARCHAR(45))  NO SQL
SELECT * FROM taikhoan WHERE taikhoan.ten_DangNhap = tenDangNhap AND taikhoan.matkhau_DangNhap = matKhau$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XuatHoaDon` (IN `sohd` INT)  NO SQL
SELECT sanpham.ten_SP, cthd_ban.sl, cthd_ban.gia_Goc , cthd_ban.tien_Giam, cthd_ban.thanhtien
FROM hoadonban, cthd_ban, sanpham
WHERE hoadonban.sohd_Ban = cthd_ban.sohd_Ban AND cthd_ban.ma_SP = sanpham.ma_SP AND hoadonban.sohd_Ban = sohd$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `baohanh`
--

CREATE TABLE `baohanh` (
  `ma_BH` int(11) NOT NULL,
  `sohd_Ban` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `serial` bigint(20) NOT NULL,
  `yeucau_BH` varchar(1000) NOT NULL,
  `nhanvien_Nhan` int(11) NOT NULL,
  `ngaynhan` date NOT NULL,
  `tinhtrang` int(11) NOT NULL,
  `nhanvien_Tra` int(11) DEFAULT NULL,
  `ngaytra` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `baohanh`
--
DELIMITER $$
CREATE TRIGGER `insert_BaoHanh` BEFORE INSERT ON `baohanh` FOR EACH ROW BEGIN
IF(NEW.ngaytra < NEW.ngaynhan)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày nhận bảo hành phải bé hơn hoặc bằng ngày trả';
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_BaoHanh` BEFORE UPDATE ON `baohanh` FOR EACH ROW BEGIN
IF(NEW.ngaytra < NEW.ngaynhan)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày nhận bảo hành phải bé hơn hoặc bằng ngày trả';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cthd_ban`
--

CREATE TABLE `cthd_ban` (
  `sohd_Ban` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `sl` int(11) DEFAULT NULL,
  `ma_KM` int(11) DEFAULT NULL,
  `gia_Goc` double DEFAULT NULL,
  `tien_Giam` double DEFAULT NULL,
  `thanhtien` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `cthd_ban`
--
DELIMITER $$
CREATE TRIGGER `delete_CTHDB` AFTER DELETE ON `cthd_ban` FOR EACH ROW BEGIN
    UPDATE hoadonban
    SET hoadonban.tongtien_Ban = hoadonban.tongtien_Ban - OLD.thanhtien
    WHERE hoadonban.sohd_Ban = OLD.sohd_Ban;
    
	UPDATE sanpham
	SET sanpham.sl = sanpham.sl + OLD.sl
	WHERE sanpham.ma_SP = OLD.ma_SP;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_CTHDB` BEFORE INSERT ON `cthd_ban` FOR EACH ROW BEGIN
	DECLARE giaban DOUBLE;
    DECLARE heso FLOAT;
    DECLARE giabangoc DOUBLE;
    DECLARE tiengiam DOUBLE;
    DECLARE thanhtien DOUBLE;
    DECLARE tongtien DOUBLE;
    
    IF (NEW.ma_KM IS NULL) THEN
    	SET heso = 0;
    ELSE
    	SELECT km.hs_KM INTO heso FROM khuyenmai km WHERE km.ma_KM = NEW.ma_KM;
    END IF;
    SELECT sanpham.gia_BanRa INTO giabangoc FROM sanpham WHERE sanpham.ma_SP = NEW.ma_SP;
    
    SET tiengiam =  giabangoc * heso;
    SET thanhtien =  (giabangoc - tiengiam)*NEW.sl;
    
    SET NEW.gia_Goc = giabangoc, NEW.tien_Giam = tiengiam, NEW.thanhtien = thanhtien;
    
    SELECT hoadonban.tongtien_Ban INTO tongtien FROM hoadonban WHERE
    hoadonban.sohd_Ban = NEW.sohd_Ban;
    SET tongtien =  tongtien + NEW.thanhtien;
    
    UPDATE hoadonban
    SET hoadonban.tongtien_Ban = tongtien
    WHERE hoadonban.sohd_Ban = NEW.sohd_Ban;
    
    UPDATE sanpham
    SET sanpham.sl = sanpham.sl - NEW.sl
    WHERE sanpham.ma_SP = NEW.ma_SP;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_CTHDB` BEFORE UPDATE ON `cthd_ban` FOR EACH ROW BEGIN
	DECLARE giaban DOUBLE;
    DECLARE heso FLOAT;
    DECLARE giabangoc DOUBLE;
    DECLARE tiengiam DOUBLE;
    DECLARE thanhtien DOUBLE;
    DECLARE tongtien DOUBLE;
    
    IF (NEW.ma_KM IS NULL) THEN
    	SET heso = 0;
    ELSE
    	SELECT km.hs_KM INTO heso FROM khuyenmai km WHERE km.ma_KM = NEW.ma_KM;
    END IF;
    SELECT sanpham.gia_BanRa INTO giabangoc FROM sanpham WHERE sanpham.ma_SP = NEW.ma_SP;
    
    SET tiengiam =  giabangoc * heso;
    SET thanhtien =  (giabangoc - tiengiam)*NEW.sl;
    
    SET NEW.gia_Goc = giabangoc, NEW.tien_Giam = tiengiam, NEW.thanhtien = thanhtien;
    
    SELECT hoadonban.tongtien_Ban INTO tongtien FROM hoadonban WHERE
    hoadonban.sohd_Ban = NEW.sohd_Ban;
    SET tongtien =  tongtien + NEW.thanhtien - OLD.thanhtien;
    
    UPDATE hoadonban
    SET hoadonban.tongtien_Ban = tongtien
    WHERE hoadonban.sohd_Ban = NEW.sohd_Ban;
    
    UPDATE sanpham
    SET sanpham.sl = sanpham.sl + NEW.sl - OLD.sl
    WHERE sanpham.ma_SP = NEW.ma_SP;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cthd_mua`
--

CREATE TABLE `cthd_mua` (
  `sohd_Mua` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `sl` int(11) NOT NULL,
  `dongia_SP` double NOT NULL,
  `thanhtien` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `cthd_mua`
--
DELIMITER $$
CREATE TRIGGER `delete_CTHDM` BEFORE DELETE ON `cthd_mua` FOR EACH ROW BEGIN
    UPDATE hoadonmua
    SET hoadonmua.tongtien_Mua = hoadonmua.tongtien_Mua - OLD.thanhtien
    WHERE hoadonmua.sohd_Mua = OLD.sohd_Mua;
    
	UPDATE sanpham
	SET sanpham.sl = sanpham.sl - OLD.sl
	WHERE sanpham.ma_SP = OLD.ma_SP;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_CTHDM` BEFORE INSERT ON `cthd_mua` FOR EACH ROW BEGIN
	DECLARE tongtien INT;
    
    SELECT hoadonmua.tongtien_Mua INTO tongtien FROM hoadonmua WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
    
    SET NEW.thanhtien = NEW.sl*NEW.dongia_SP;
    
    SET tongtien = tongtien + NEW.thanhtien;
    
    UPDATE hoadonmua SET hoadonmua.tongtien_Mua = tongtien WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
    
    UPDATE sanpham
    SET sanpham.sl = sanpham.sl + NEW.sl
    WHERE sanpham.ma_SP = NEW.ma_SP;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_CTHDM` BEFORE UPDATE ON `cthd_mua` FOR EACH ROW BEGIN
	DECLARE tongtien INT;
    
    SELECT hoadonmua.tongtien_Mua INTO tongtien FROM hoadonmua WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
    
    SET NEW.thanhtien = NEW.sl*NEW.dongia_SP;
    
    SET tongtien = tongtien + NEW.thanhtien - OLD.thanhtien;
    
    UPDATE hoadonmua SET hoadonmua.tongtien_Mua = tongtien WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
    
    UPDATE sanpham
    SET sanpham.sl = sanpham.sl + NEW.sl - OLD.sl
    WHERE sanpham.ma_SP = NEW.ma_SP;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ctkm`
--

CREATE TABLE `ctkm` (
  `ma_KM` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `doanhthu`
--

CREATE TABLE `doanhthu` (
  `thang` int(11) NOT NULL,
  `nam` int(11) NOT NULL,
  `quy` int(11) NOT NULL,
  `tienban_SP` double DEFAULT '0',
  `tienmua_SP` double DEFAULT '0',
  `tienluong_NV` double DEFAULT '0',
  `tienloi` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `doanhthu`
--

INSERT INTO `doanhthu` (`thang`, `nam`, `quy`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES
(1, 2019, 1, 0, 0, 14000000, -14000000);

--
-- Triggers `doanhthu`
--
DELIMITER $$
CREATE TRIGGER `insert_doanhthu` BEFORE INSERT ON `doanhthu` FOR EACH ROW BEGIN
	DECLARE luong DOUBLE;
    SELECT SUM(nhanvien.luong_CB) INTO luong FROM nhanvien WHERE nhanvien.tinh_trang = 1;
    SET NEW.tienluong_NV = luong + NEW.tienluong_NV;
    SET NEW.tienloi = NEW.tienban_SP - NEW.tienmua_SP - NEW.tienluong_NV;
    IF( NEW.thang >= 1 AND NEW.thang <=3) THEN
    	SET NEW.quy = 1;
    ELSEIF (NEW.thang >= 4 AND NEW.thang <=6) THEN
    	SET NEW.quy = 2;
    ELSEIF (NEW.thang >= 7 AND NEW.thang <=9) THEN
    	SET NEW.quy = 3;
    ELSE
    	SET NEW.quy = 4;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_doanhthu` BEFORE UPDATE ON `doanhthu` FOR EACH ROW BEGIN
    SET NEW.tienloi = NEW.tienban_SP - NEW.tienmua_SP - NEW.tienluong_NV;
    IF( NEW.thang >= 1 AND NEW.thang <=3) THEN
    	SET NEW.quy = 1;
    ELSEIF (NEW.thang >= 4 AND NEW.thang <=6) THEN
    	SET NEW.quy = 2;
    ELSEIF (NEW.thang >= 7 AND NEW.thang <=9) THEN
    	SET NEW.quy = 3;
    ELSE
    	SET NEW.quy = 4;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `hoadonban`
--

CREATE TABLE `hoadonban` (
  `sohd_Ban` int(11) NOT NULL,
  `ngay_Ban` date NOT NULL,
  `ma_NV` int(11) DEFAULT NULL,
  `ma_KH` int(11) DEFAULT NULL,
  `tongtien_Ban` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `hoadonban`
--
DELIMITER $$
CREATE TRIGGER `delete_hoadonban` BEFORE DELETE ON `hoadonban` FOR EACH ROW BEGIN
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienhh DOUBLE;
    DECLARE hs DOUBLE DEFAULT 0.03;
    DECLARE tienban DOUBLE;
    
    SET nam = year(OLD.ngay_Ban), thang = month(OLD.ngay_Ban);
    
    SELECT hoahong.tien_HH INTO tienhh FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = OLD.ma_NV;
        
    SET tienhh = tienhh - OLD.tongtien_Ban*hs;
        
    UPDATE hoahong SET hoahong.tien_HH = tienhh  WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = OLD.ma_NV;
    
    SELECT doanhthu.tienban_SP INTO tienban FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
    SET tienban = tienban - OLD.tongtien_Ban;
        
    UPDATE doanhthu SET doanhthu.tienban_SP = tienban WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_hoadonban` AFTER INSERT ON `hoadonban` FOR EACH ROW BEGIN
	DECLARE flag1 INT;
    DECLARE flag2 INT;
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienhh DOUBLE;
    DECLARE hs DOUBLE DEFAULT 0.03;
    DECLARE tienban DOUBLE;
    DECLARE tienmua DOUBLE;
    DECLARE luong DOUBLE;
    DECLARE tienloi DOUBLE;
    
    SET nam = year(NEW.ngay_Ban), thang = month(NEW.ngay_Ban);
    
    SELECT COUNT(*) INTO flag1 FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    
    IF(flag1 = 0) THEN
    BEGIN
    	INSERT INTO `hoahong` (`ma_NV`, `thang`, `nam`, `tien_HH`) VALUES (NEW.ma_NV, thang, nam, NEW.tongtien_Ban); 
    END;
    ELSE
    BEGIN
    	SELECT hoahong.tien_HH INTO tienhh FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
        
        SET tienhh = tienhh + NEW.tongtien_Ban*hs;
        
        UPDATE hoahong SET hoahong.tien_HH = tienhh  WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    END;
    END IF;
    
    SELECT COUNT(*) INTO flag2 FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    
    IF(flag2 = 0) THEN
    BEGIN
    	SELECT SUM(nhanvien.luong_CB) INTO luong FROM nhanvien WHERE nhanvien.tinh_trang = 1;
        SET tienloi = NEW.tongtien_Ban - luong;
    	INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES (thang, nam, NEW.tongtien_Ban, '0', luong, tienloi);
    END;
    ELSE
    BEGIN
    	SELECT doanhthu.tienban_SP INTO tienban FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        SELECT doanhthu.tienmua_SP INTO tienmua FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        SELECT doanhthu.tienluong_NV INTO luong FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
        SET tienban = tienban + NEW.tongtien_Ban;
        SET tienloi = tienban - tienmua - luong;
        
        UPDATE doanhthu SET doanhthu.tienban_SP = tienban, doanhthu.tienloi = tienloi WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    END;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_hoadonban` AFTER UPDATE ON `hoadonban` FOR EACH ROW BEGIN
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienhh DOUBLE;
    DECLARE hs DOUBLE DEFAULT 0.03;
    DECLARE tienban DOUBLE;
    
    SET nam = year(NEW.ngay_Ban), thang = month(NEW.ngay_Ban);
    
    SELECT hoahong.tien_HH INTO tienhh FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
        
    SET tienhh = tienhh + (NEW.tongtien_Ban - OLD.tongtien_Ban)*hs;
        
    UPDATE hoahong SET hoahong.tien_HH = tienhh  WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    
    SELECT doanhthu.tienban_SP INTO tienban FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
    SET tienban = tienban + NEW.tongtien_Ban - OLD.tongtien_Ban;
        
    UPDATE doanhthu SET doanhthu.tienban_SP = tienban WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `hoadonmua`
--

CREATE TABLE `hoadonmua` (
  `sohd_Mua` int(11) NOT NULL,
  `ngay_Nhap` date DEFAULT NULL,
  `ma_NCC` int(11) DEFAULT NULL,
  `ma_NV` int(11) NOT NULL,
  `tongtien_Mua` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `hoadonmua`
--
DELIMITER $$
CREATE TRIGGER `insert_hoadonmua` AFTER INSERT ON `hoadonmua` FOR EACH ROW BEGIN
	DECLARE flag INT;
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienmua DOUBLE;
    
    SET thang = month(NEW.ngay_Nhap), nam = year(NEW.ngay_Nhap);
    
    SELECT COUNT(*) INTO flag FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    
    IF(flag = 0) THEN
    BEGIN
    	INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES (thang, nam, '0', NEW.tongtien_Mua, '0', '0');
    END;
    ELSE
    BEGIN
    	SELECT doanhthu.tienmua_SP INTO tienmua FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
        SET tienmua = tienmua + NEW.tongtien_Mua;
        
        UPDATE doanhthu SET doanhthu.tienmua_SP = tienmua WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    END;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_hoadonmua` AFTER UPDATE ON `hoadonmua` FOR EACH ROW BEGIN
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienmua DOUBLE;
    
    SET thang = month(NEW.ngay_Nhap), nam = year(NEW.ngay_Nhap);
    
    SELECT doanhthu.tienmua_SP INTO tienmua FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
    SET tienmua = tienmua + NEW.tongtien_Mua - OLD.tongtien_Mua;
        
    UPDATE doanhthu SET doanhthu.tienmua_SP = tienmua WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `hoahong`
--

CREATE TABLE `hoahong` (
  `thang` int(2) NOT NULL,
  `nam` int(4) NOT NULL,
  `ma_NV` int(11) NOT NULL,
  `tien_HH` double(255,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hoahong`
--

INSERT INTO `hoahong` (`thang`, `nam`, `ma_NV`, `tien_HH`) VALUES
(1, 2019, 10, 0),
(1, 2019, 11, 0),
(1, 2019, 12, 0),
(1, 2019, 13, 0),
(1, 2019, 14, 0);

--
-- Triggers `hoahong`
--
DELIMITER $$
CREATE TRIGGER `insert_hoahong` AFTER INSERT ON `hoahong` FOR EACH ROW BEGIN
	DECLARE flag INT;
    DECLARE tongluong DOUBLE;
    DECLARE luong DOUBLE;
    
    SET luong = NEW.tien_HH;
    
    SELECT COUNT(*) INTO flag FROM doanhthu WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
    
    IF(flag = 0) THEN
    BEGIN
    	INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES (NEW.thang, NEW.nam, '0', '0', luong, '0');
    END;
    ELSE
    BEGIN
    	SELECT doanhthu.tienluong_NV INTO tongluong FROM doanhthu WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
        
        SET tongluong = tongluong + luong;
        
        UPDATE doanhthu SET doanhthu.tienluong_NV = tongluong WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
    END;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_hoahong` AFTER UPDATE ON `hoahong` FOR EACH ROW BEGIN
    DECLARE tongluong DOUBLE;
    
    SELECT doanhthu.tienluong_NV INTO tongluong FROM doanhthu WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
        
    SET tongluong = tongluong + NEW.tien_HH - OLD.tien_HH;
        
    UPDATE doanhthu SET doanhthu.tienluong_NV = tongluong WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `ma_KH` int(11) NOT NULL,
  `ten_KH` varchar(50) DEFAULT NULL,
  `soCMND_KH` int(11) DEFAULT NULL,
  `diachi_KH` varchar(255) DEFAULT NULL,
  `soDT_KH` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ngaysinh_KH` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `ma_KM` int(11) NOT NULL,
  `ten_KM` varchar(45) NOT NULL,
  `hs_KM` float NOT NULL,
  `ngay_BD` date NOT NULL,
  `ngay_KT` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `khuyenmai`
--
DELIMITER $$
CREATE TRIGGER `insert_KhuyenMai` BEFORE INSERT ON `khuyenmai` FOR EACH ROW BEGIN
IF(NEW.ngay_KT < NEW.ngay_BD)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày bắt đầu khuyến mãi phải bé hơn hoặc bằng ngày kết thúc';
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_KhuyenMai` BEFORE UPDATE ON `khuyenmai` FOR EACH ROW BEGIN
IF(NEW.ngay_KT < NEW.ngay_BD)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày bắt đầu khuyến mãi phải bé hơn hoặc bằng ngày kết thúc';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `ma_NCC` int(11) NOT NULL,
  `ten_NCC` varchar(45) NOT NULL,
  `diachi_NCC` varchar(45) NOT NULL,
  `soDT_NCC` int(11) NOT NULL,
  `tinh_trang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `ma_NV` int(11) NOT NULL,
  `ten_NV` varchar(50) NOT NULL,
  `soCMND_NV` int(9) DEFAULT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `ngaysinh_NV` date NOT NULL,
  `diachi_NV` varchar(255) DEFAULT NULL,
  `soDT_NV` int(11) DEFAULT NULL,
  `ngay_VaoLam` date DEFAULT NULL,
  `luong_CB` double NOT NULL,
  `tinh_trang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`ma_NV`, `ten_NV`, `soCMND_NV`, `gioitinh`, `ngaysinh_NV`, `diachi_NV`, `soDT_NV`, `ngay_VaoLam`, `luong_CB`, `tinh_trang`) VALUES
(10, 'Heo Solji', 241609133, 1, '1989-01-10', '12 Nguyễn Kiệm, quận Gò Vấp, thành phố Hồ Chí Minh', 32689213, '2016-03-16', 3000000, 1),
(11, 'Ahn Hyojin', 241609158, 1, '1991-12-10', '12 Nguyễn Kiệm, quận Gò Vấp, thành phố Hồ Chí Minh', 389625458, '2016-03-16', 3000000, 1),
(12, 'Ahn Heeyeon', 241609835, 1, '1992-05-01', '35 Cách mạng tháng Tám, quận 10, thành phố Hồ Chí Minh', 325648125, '2016-07-05', 3000000, 1),
(13, 'Seo Hyelin', 241609556, 1, '1993-08-23', '156 Võ Văn Ngân, quận Thủ Đức, thành phố Hồ Chí Minh', 378923648, '2017-05-17', 2500000, 1),
(14, 'Park Jeonghwa', 241609784, 1, '1995-05-08', '208 Nguyễn Oanh, quận Gò Vấp, thành phố Hồ Chí Minh', 378952365, '2017-05-17', 2500000, 1);

--
-- Triggers `nhanvien`
--
DELIMITER $$
CREATE TRIGGER `insert_NhanVien` BEFORE INSERT ON `nhanvien` FOR EACH ROW BEGIN
IF TIMESTAMPDIFF(YEAR,NEW.ngaysinh_NV,NEW.ngay_VaoLam) < 18
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Nhân viên phải đủ 18 tuổi';
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_NhanVien` BEFORE UPDATE ON `nhanvien` FOR EACH ROW BEGIN
IF TIMESTAMPDIFF(YEAR,NEW.ngaysinh_NV,NEW.ngay_VaoLam) < 18
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Nhân viên phải đủ 18 tuổi';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `nhasanxuat`
--

CREATE TABLE `nhasanxuat` (
  `ma_nsx` int(11) NOT NULL,
  `ten_nsx` varchar(45) NOT NULL,
  `thongtin` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `ma_PhanQuyen` int(11) NOT NULL,
  `quyentruycap` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`ma_PhanQuyen`, `quyentruycap`) VALUES
(1, 'admin'),
(2, 'nhanvien');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `ma_SP` int(11) NOT NULL,
  `ten_SP` varchar(45) DEFAULT NULL,
  `ma_nsx` int(45) DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `nam_SX` int(4) DEFAULT NULL,
  `gia_BanRa` double DEFAULT NULL,
  `thoigian_BH` int(11) DEFAULT NULL,
  `xuatxu` varchar(45) DEFAULT NULL,
  `mau` varchar(45) DEFAULT NULL,
  `bonho` varchar(45) DEFAULT NULL,
  `kichthuoc` varchar(45) DEFAULT NULL,
  `anh` longblob,
  `tinhtrang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ten_DangNhap` varchar(45) NOT NULL,
  `matkhau_DangNhap` varchar(45) DEFAULT NULL,
  `ma_PhanQuyen` int(11) DEFAULT NULL,
  `ma_NV` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`ten_DangNhap`, `matkhau_DangNhap`, `ma_PhanQuyen`, `ma_NV`) VALUES
('admin', 'C31F804A0E4A8943A7A5577A292F2321', 1, NULL),
('nhanvien1', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 10),
('nhanvien2', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 11),
('nhanvien3', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 12),
('nhanvien4', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 13),
('nhanvien5', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 14);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `baohanh`
--
ALTER TABLE `baohanh`
  ADD PRIMARY KEY (`ma_BH`),
  ADD UNIQUE KEY `nhanvien_Tra` (`nhanvien_Tra`),
  ADD UNIQUE KEY `UK_3wsc3w3hlrxns7qbjed5381fo` (`nhanvien_Tra`),
  ADD KEY `sohd_Ban` (`sohd_Ban`),
  ADD KEY `ma_SP` (`ma_SP`),
  ADD KEY `nhanvien_Nhan` (`nhanvien_Nhan`);

--
-- Indexes for table `cthd_ban`
--
ALTER TABLE `cthd_ban`
  ADD PRIMARY KEY (`sohd_Ban`,`ma_SP`),
  ADD KEY `fk_CTHDB_KM` (`ma_KM`),
  ADD KEY `fk_CTHDB_SP` (`ma_SP`);

--
-- Indexes for table `cthd_mua`
--
ALTER TABLE `cthd_mua`
  ADD PRIMARY KEY (`sohd_Mua`,`ma_SP`),
  ADD KEY `fk_CTHDM_SP` (`ma_SP`);

--
-- Indexes for table `ctkm`
--
ALTER TABLE `ctkm`
  ADD PRIMARY KEY (`ma_KM`,`ma_SP`),
  ADD KEY `fk_CTKM_SP` (`ma_SP`);

--
-- Indexes for table `doanhthu`
--
ALTER TABLE `doanhthu`
  ADD PRIMARY KEY (`thang`,`nam`);

--
-- Indexes for table `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD PRIMARY KEY (`sohd_Ban`),
  ADD KEY `fk_hd_kh` (`ma_KH`),
  ADD KEY `fk_hd_nv` (`ma_NV`);

--
-- Indexes for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  ADD PRIMARY KEY (`sohd_Mua`),
  ADD KEY `fk_HDM_NCC` (`ma_NCC`),
  ADD KEY `ma_NV` (`ma_NV`);

--
-- Indexes for table `hoahong`
--
ALTER TABLE `hoahong`
  ADD PRIMARY KEY (`ma_NV`,`thang`,`nam`),
  ADD KEY `ma_NV` (`ma_NV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`ma_KH`),
  ADD UNIQUE KEY `soCMND_KH` (`soCMND_KH`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`ma_KM`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`ma_NCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`ma_NV`),
  ADD UNIQUE KEY `soCMND_NV` (`soCMND_NV`);

--
-- Indexes for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD PRIMARY KEY (`ma_nsx`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`ma_PhanQuyen`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`ma_SP`),
  ADD KEY `ma_nsx` (`ma_nsx`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ten_DangNhap`),
  ADD KEY `fk_TK_NV` (`ma_NV`),
  ADD KEY `fk_TK_PQ` (`ma_PhanQuyen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `baohanh`
--
ALTER TABLE `baohanh`
  MODIFY `ma_BH` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ctkm`
--
ALTER TABLE `ctkm`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoadonban`
--
ALTER TABLE `hoadonban`
  MODIFY `sohd_Ban` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  MODIFY `sohd_Mua` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `ma_KH` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `ma_NCC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `ma_NV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  MODIFY `ma_nsx` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `ma_PhanQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `ma_SP` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `baohanh`
--
ALTER TABLE `baohanh`
  ADD CONSTRAINT `baohanh_ibfk_1` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`),
  ADD CONSTRAINT `baohanh_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `baohanh_ibfk_3` FOREIGN KEY (`nhanvien_Nhan`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `baohanh_ibfk_4` FOREIGN KEY (`nhanvien_Tra`) REFERENCES `nhanvien` (`ma_NV`);

--
-- Constraints for table `cthd_ban`
--
ALTER TABLE `cthd_ban`
  ADD CONSTRAINT `cthd_ban_ibfk_1` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`),
  ADD CONSTRAINT `cthd_ban_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `cthd_ban_ibfk_3` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`);

--
-- Constraints for table `cthd_mua`
--
ALTER TABLE `cthd_mua`
  ADD CONSTRAINT `cthd_mua_ibfk_1` FOREIGN KEY (`sohd_Mua`) REFERENCES `hoadonmua` (`sohd_Mua`),
  ADD CONSTRAINT `cthd_mua_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`);

--
-- Constraints for table `ctkm`
--
ALTER TABLE `ctkm`
  ADD CONSTRAINT `ctkm_ibfk_1` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `ctkm_ibfk_2` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`);

--
-- Constraints for table `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD CONSTRAINT `hoadonban_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `hoadonban_ibfk_2` FOREIGN KEY (`ma_KH`) REFERENCES `khachhang` (`ma_KH`);

--
-- Constraints for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  ADD CONSTRAINT `hoadonmua_ibfk_1` FOREIGN KEY (`ma_NCC`) REFERENCES `nhacungcap` (`ma_NCC`),
  ADD CONSTRAINT `hoadonmua_ibfk_2` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`);

--
-- Constraints for table `hoahong`
--
ALTER TABLE `hoahong`
  ADD CONSTRAINT `hoahong_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`ma_nsx`) REFERENCES `nhasanxuat` (`ma_nsx`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`ma_PhanQuyen`) REFERENCES `phanquyen` (`ma_PhanQuyen`);
COMMIT;