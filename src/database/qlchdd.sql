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
-- Dumping data for table `cthd_mua`
--

INSERT INTO `cthd_mua` (`sohd_Mua`, `ma_SP`, `sl`, `dongia_SP`, `thanhtien`) VALUES
(3, 1, 3, 1000, 3000),
(3, 2, 3, 20000, 60000);

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

--
-- Dumping data for table `ctkm`
--

INSERT INTO `ctkm` (`ma_KM`, `ma_SP`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(5, 1),
(5, 2),
(5, 3),
(5, 4);

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
(1, 2019, 1, 0, 0, 3000, -3000);

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
-- Dumping data for table `hoadonmua`
--

INSERT INTO `hoadonmua` (`sohd_Mua`, `ngay_Nhap`, `ma_NCC`, `ma_NV`, `tongtien_Mua`) VALUES
(1, '2019-01-01', 2, 5, 0),
(3, '2019-01-01', 1, 5, 63000);

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
(1, 2019, 3, 0),
(1, 2019, 4, 0),
(1, 2019, 5, 0);

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
  `ten_KH` varchar(45) DEFAULT NULL,
  `soCMND_KH` int(11) DEFAULT NULL,
  `diachi_KH` varchar(45) DEFAULT NULL,
  `soDT_KH` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ngaysinh_KH` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`ma_KH`, `ten_KH`, `soCMND_KH`, `diachi_KH`, `soDT_KH`, `email`, `ngaysinh_KH`) VALUES
(1, 'Nguyễn Văn Hậu', 321643275, 'Bình Thạnh', 348249328, NULL, NULL),
(2, 'Luis Alberto Suárez Díaz', 56789, 'Uruguay', 13456, NULL, NULL),
(3, 'Edson Arantes do Nascimento', 6789, 'Brazil', 5432, NULL, NULL),
(4, 'Vincent Willem van Gogh', 3444, 'Hà Lan', 333333, NULL, NULL),
(5, 'William Sydney Porter', 32156, 'Mỹ', 777887, NULL, NULL),
(6, 'long', 147258369, 'hà nội', 12345, 'thăng', NULL);

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
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`ma_KM`, `ten_KM`, `hs_KM`, `ngay_BD`, `ngay_KT`) VALUES
(1, 'Quốc Khánh', 0, '2018-09-02', '2018-09-02'),
(2, '30/4 - 1/5', 0.1, '2018-04-29', '2018-05-01'),
(3, '20/11/2018', 0.05, '2018-11-19', '2018-11-20'),
(4, 'Noel 2018', 0.07, '2018-12-23', '2018-12-25'),
(5, 'Tết dương lịch 2019', 0.15, '2018-12-29', '2019-01-01');

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

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`ma_NCC`, `ten_NCC`, `diachi_NCC`, `soDT_NCC`, `tinh_trang`) VALUES
(1, 'Alpha', 'Quận 2', 23456, 1),
(2, 'Higa', 'Gò Vấp', 19567, 1),
(3, 'LaLa', 'Quận 9', 666777, 1),
(4, 'Syb', 'Bình Chánh', 5656, 1),
(5, 'fpt', 'hà nội', 123456789, 1),
(6, 'sahara', 'lái thiêu', 123457, 1),
(7, 'lulu', 'tèo', 123456789, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `ma_NV` int(11) NOT NULL,
  `ten_NV` varchar(255) NOT NULL,
  `soCMND_NV` int(9) DEFAULT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `ngaysinh_NV` date NOT NULL,
  `diachi_NV` varchar(45) DEFAULT NULL,
  `soDT_NV` int(11) DEFAULT NULL,
  `ngay_VaoLam` date DEFAULT NULL,
  `luong_CB` double NOT NULL,
  `tinh_trang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`ma_NV`, `ten_NV`, `soCMND_NV`, `gioitinh`, `ngaysinh_NV`, `diachi_NV`, `soDT_NV`, `ngay_VaoLam`, `luong_CB`, `tinh_trang`) VALUES
(3, 'Hồ Thái Thăng', 123456789, 0, '1998-02-01', 'ko có', 1234, '3918-11-01', 1000, 1),
(4, 'Loui Pasteur', 345345, 1, '1822-12-27', 'Pháp', 12345, '2018-11-01', 1000, 1),
(5, 'Thomas Alva Edison', 7878, 1, '1847-02-11', 'Mỹ', 567432, '2018-01-01', 1000, 1),
(6, 'yến', 46354654, 0, '2000-12-01', 'sdfsadfsd', 926528212, '2019-01-02', 0, 0),
(9, 'yến', 464654, 0, '2000-12-01', 'sdfsadfsd', 546, '2019-01-02', 0, 0);

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

--
-- Dumping data for table `nhasanxuat`
--

INSERT INTO `nhasanxuat` (`ma_nsx`, `ten_nsx`, `thongtin`) VALUES
(1, 'ABC', 'trung quốc'),
(2, 'XYZ', ''),
(3, 'GHI', ''),
(4, 'SamSung', 'Hàn Quốc');

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

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(1, 'iPhone 6 32GB', 1, 14, 2017, 7000000, 12, 'Trung Quốc', 'Hồng', '32G', NULL, NULL, 1);
INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(2, 'Samsung Galaxy A9', 3, 13, 2018, 12500000, 1, 'hồng kông', 'Đen', '128GB', '6.3 inch Full HD+ (1080 x 2220 Pixels)', 0x89504e470d0a1a0a0000000d494844520000012c0000010e080600000033192a48000080004944415478daec7d075894d7b6f6bdf7bff79c7b5ace494e7a318926f60ea2144111505410ec58502cf436c0d0cbd07b6f52141145c54a50633789893189e93189a9a619638a89262accb7f7fbaf35cc18921b75504c44673dcff70832e52b7bbffb5d6baff5aefff80f9399cc64263399c94c66329399cc64263399c94c66329399cc64263399c94c66329399cc64263399c94c66329399cc64b79a351dc59fd21b714fd24aadd3dc6c11ee9e22f24784cadde64172ef607f3c651e2cf75a86c916972451e0952f7c626b95494d07f0777e9fe9ee99cc6426fb5d6dc7fbf873f6263c9858afcc9a93238adc5365b345a83c691624bf26c0fa9e00eb6b02ac8f5d34f209af3c911cbb5cf16cda837f1e3880ff35dd3d9399ec374cd382bf66d4e3df490d171e8baad65aa9aab50e3e45adb3bcf294051e596d3e8b0ab4e1d3d3b491e641adb1b61122ce3e0af1a342459c75b836d63eaa35dabb481bee5f2e54f3b3db82bd7215afc505caec9ae61fef59bde3dbdb6ed57bdab013f7adda0d739724b9774c94fca0af37d0cfa7fd20a0d21d4302daff1de407f4e7bfd16bf875f6d1f23dd714d150b5533bb6621b6e378d5093dd5206e03f3507f0df054df88b5f056ef72ac15d9e59e8e19a84c79c62d0774202464cd4c0d6355571754f56bcdc52859f6bb288999828929c1344baab465be814a72d1e1ea8945b858a0aeb085161162cca47048bb2512aa5d42d4514cecc10b9b3334512bb350165caecaa96337796379dfabb0e109bf0274d15feea5f8e7b1765e3fe19697840558b3b824a709bee6f1afcd7cd74af57efc06dc11530f32ec21cc758f9824db8fc8c81aabf6ffb61002b0360f131c0570f5a74d844c84f1de3e4769f6278472e479f12fa3cd32836d9adc3a008ac329a7057fabad6810426339716b4f9cfc9522a26931b4213ea79eb08f9c9a83079ca2c181786f843f08a3f808e81fa832719ffde9111f004e3c9d57b29fd4b3f0f0fc29929c9f2b9a5452235ad51f16097e6c811fc0f7f3f4db807350d6dc3bd8b44ec823c6db247a63623a852991bbe4ceba8597dee414dddcde3fed0b5feb9fa498c9f9424aa8607ca1fe81e09062203401973f07da5fb2be8e756af22242edb8e09a6516cb29b169c7257e16f9a55e8e155d8367c768e76b47d343c1ca2e0372e0689632345151d8d6322e553b492bf6e19263f1c1122bf25b03a332c105a022ca903a50e8701b8743ffbea26931c1288f3f49e2f09e8de340fc196916158ee9682489f62654ac6dab691994d78ccbb0cc3dd92e13c2e0a21b65122d52e526e2177a7d9214eb678158878df62657e66d385c7d825359c7f5313fe1f4f7afeb7bb31af9623f86bf65adc3f335d6411abda3fc45f9ee77b35d0af7380a5bfdf92eeb7767c82dc48cc55b3ed206e37c5b54c76d359154dfeb4b5784853af4c5e4ca0303b5ba922707a9780e9c4b020b4115392fdf5eca87f3bf8b48391df2f5d955f1f06b7451f87915661f87a42bc7c664991284e5bd36651be0b0fe90013f82f9a58ffad69505c896d45b9a7c82709d8bea1ef557a2dd67d571bfd7e6e66a6dcbc305fa4e46f6e1bd2d1e5a9a349c9711b062d06dfee74ef77bf82fb53d662a445a83c3534003ff6f7b9fc3dbddcc18b437f1d73956746aae4d1ad87d19f00f14ed3083759b7675439eb2ef40a29d3da2e2e50e6d8c7884c1bb5584600f5240df497e8789b00e23481d54f3411c445e6e4f73383ea1804fef5a419a877ffe8f70bf49927c746c9bd4ef1a2c13e0a4b276930756931ac72b6a057f6bad67efee58acbf878113e3a42e49a05c99d16c1383c32149fdaaaf19e5d947c796c94a8b68d4012819ddfac4cad13b93b234b36e1c184e56d4342aab436535311e41027524785a1da2e52e44c88853aa2463b465d877b19086ff46711b61cae3e2588a77b769e0eed20bfab032bdd02a1bfff04f46df4fb978195880c58064bd3883759b7350ef03213c95aabb50aaa143e8b0b4505b95def11487ddd31886b0c83ba946bc2ab3cef5cd1ef3f3a44cb7717e48bc2e815c29b738576d077f37954eec2dd09f56dc302cab4c9e3e3e47eeb70f945af4540ef25ed60e714275f9e9e2637c7d52bb35256b70e5ab50b7fe373af3a82ffa9da837f46d6691d43ab9425d3d2e476725fdf2217f3dce80879dc31461e88ac6ef38fa96bed7ba01bb02dbf0a84791560fde34bdaeffdd58255c783ef7d7f3ffce85528d62f2cc454d3a83759b7328eed1435e39edc0dda31d3d2451a319a35c383f0ceb0401ca7017e8a99101ddac11d98d3e04e4c90017e3fef668d08c177b691f2c359f43d1e99ca92890918e4958f87621b714ff576f4f62b55dc3c32451abdeee9218178b3af0f4ed0b99c1aa5c237d3d2c4baa96922dd3d0d53dc5330c42d15bda2d7e0f6ccb578a4606beba0d959227e6282a8b450e1396212eff4f7c67172a3bea7f77ec96900f4deba79d95a8da6413b26bd01f7f1755f77b64aeeb4a60eff8a5b71eea1b0ea0bbd832b5b0706556847fb97101b2c6c9d3e374759342b43f839c66823e9fce2e7e78864578d36db395e9b3746ad2db20a932f59aa7062a0dfd5bb82bfb568d0fd51e8be9c1c1f8f944d07d1f3f7b8172633599718338dfcad78286fa3e2469366f5f878798406b4e4e02eafc603f4aeded54e900e712a5884e22b87587934ac5a59cac0c16e1933239e3095db5a07fa942a8b0978d69805e11b7a9ff6112f1d38fe345285effc4a4509b3beaa1d177ad5750816ebce7d53db088f6c51e39c20f75a84e06c1f6f28ccc8989510f87d4ddff9b64796b67049a108cd6e6ab32869c25dbfc724cd6ec63f348db833baf6c263eaeab6a121556d23032a95c93ea5adb3bcf2dbfce6e68ab8d919dad4f1b14a19ddfb9a79b9a27e8a46bb81006bcb9848ed36f360f9092d1c3f5dcac5be2ad7b0fdb3247dee05876814343d83fe07ba596ccf64b798dbc7e901996bdb86a5372a332724cae76cd4f2439edc86637040e758d46f6da3f7d37fd63872fbdc52e49ed446c52db5017d0ce791b119ffce6dd2dacfcc12659335f2008323a7343cb6182037f418fddfaed87a6549ca6a0ce2dd327e0fe77d311bf42f57162e2c10e9f6d1f21831b0eff87d7cd0443c4b6cea7d02bde5fea5222e6b5ddb90f44db88fafb9abe37cec3a676ebad03385ee63549de2c260e899ab4d999aaaac9994a86c7388510e912bfb29bbd3748e17860642e9b06377314f8a0f5d5a873ed17340c78d8b2e04aa5f8316dfefb1517253c22a65a18aeeab696698ec8633def1d3d4e28e192918342f5799e99923a2c746cbd74785e173c344e163f0d5afdc8609f613b963dfd02afe8163ac6c9e9a2c2a52d76ac73060b18be49a8a3e5353603d3353f84e4812eb8901bd44eff99e26ee099ac0c70874b64ed688b2f83ac52d7d1d061238dc3527133dd9159c140f07d75491447f5f4ec0c629145f0cf4c7d734c1df1b168423fcded9d92221a04c5994b1ee42ef3c6238570b58ba548826fc89d33a8256e02eef42dc374d839ece1af4774ec0b029698acbb434c56366ba50b9268b3c178da8191f279f1c17ad1cb4558bd709ac4ed2f99da6fbd04a8025741b0f7ebf042d3e74ffa707b081bfda65bd5e80c5e068ab965b99ed7a57fd9c06623293dd3056da0cf3e8158ac7c444f912b967270d2c68a06f174c009f9f8f4989f2e5d999a2aea85971cd6b69df3a67d0c8588dfeccb45c92e5d3a323e5079c96402bbda4ef6f9d9121b77b1588e4f4f5dab11df3a8b29bd0a7a0496b373b4b6ee02c6f4e7e7c7c09241d8a854a7e472eecd30bf2456d6283d691135999fd7415a3e2007ede263c9cd1a8b5542d13bebea522c92d596e748a95cfd2647fcf2c18e70988b486385d3fdf9fe37503f4a033c8effa80ce353d2fbdab4e60ba7b62bc36867cf3bf9b6687c96e08dbb50b7f0badc4235eb95afb498948181f276aac23e47172537e30649f5fcd76f9e00e49a0f4b39663452343f1f2c830b9736202e23db2b1a0700b8632ab0a28c38031d1984547d4d81851315a2ddf2577e943f310f9ae79309eb408c6eae9e908f22a50a6e435b5f62716d697d8cc70c738cce0f78c8914799cc6601321df1e158a2f2c42e461f350b9cb2a5c944f4a14510bf215cff855adfd38c99293433b0358ba32237a4f5613fe195b837b024b5b077964c1665aba76bc433c968e8b439843acc8728811abc9bd7d6274847cd9324cbe6f112abf22e6d46648ed18f8ab6cfe8ee91dbf06acc1bf3a06f9ffc6719d818e1729b360f99abd5a3404556054ea2a3cca8a0edd21ddc36437b1b51cc203a1558adbc27cb186c0e1eb81be3fa7265ccb801f6448fcf4d64dac739313e52b0bf27465342e06d0e0c11fbd42ebe0572602c8557b8726e7373d17e9ded766a1c2479e79627944ade252de8cc70c1b001c508faf57a67b178b04c718f9167dd7771cd7ea43df333c18a79c13e461fabca8b895cab4ba2df857893e15e2aae35174ae9c0e91bae6c2e3712bb4565c743d375b544e4b934fd844e0043151765575f74c9712e0f3738ce96a5cb6df02a98e3ba917d347bcaf6db3e38acf8fbed32c08df5b85c94f23aa454cea1a65ea8ec3b8cd50fe643293fdaec6a52c19eb614d8c6619b92f4f8c52c9f769a09ebf1637c5c0aa78420d0dc039fa4cdef5dbe89c200a39e9737e01ccd3d6e0e1943530f3ccc594b1512299dc8ecdb4921fb408c149028037c7c6e0805d24121ce2e04dafb7633655fa041e0d2853a64c4946809d1a05cc9ecc83e50b9661f89218cd6b63a2e53376d122636c2c54cef170f32fc7d07866044df8536776fb189c1810f3d7e2a18cd5adfd17142833ddd384af639c48b6528955c40eb78c54c9439c094e0c8a59e84fe4f2b51aea1d0d6cf472c1700388752c41d21dfe170148b21b393c58fe440cf36bba3f27b89c89d9235f2b97e0d8aa95a71ca2e5f3b4c09cbe5e2c8b3f979e612bb1c4b3566178617424b639c521774921a6d2d831ebea8d0a9399ecb2462e8e4de67a6511ada05f0f0f9267fb2cfd99155c0babe2cfe0cfa281febd7db47c3fa842a833d62b133b7e37b1ac69f373442ab1aab78999fc480c49d0e438eb148f677c4a4459e166f45bb10377f16b0940fe55dc821ec49a525d35b2d93a1c271f5b022d1d0a01c6594e55f02a10559cba402ee623d7724f18dc9637e31f394d6d43931bb58e0bf3b5855353e446667266c1baf401d1311637f02a523a3a8255ff5fc5b4f405df92770d09104f13507dc440e510ad3c3f3141ee9992229bdd92959299e94ab957bea81fa5c2e7d7d32decf83c99258f8dc2c75e852223739d328301cb045a26bbae76e810fe12568dde8b0a44fa6835f60e09c4f1c1ed499fba38cbd5eefe195c226206e74684e2db39d922df230b3eae2918a62ec7bd694d78206b3d6ca7a723c129161be8b51fd07bbe181e803363a3e593931245c5a4448ce55d3eef62f4a8df8707226bdb2ce6e58a281bb5dc4213f8254e12a5cf3f69a9c229178d5835355da4b82563dc8c2c0c5a9c87877505d84d9d53cd6480e224514d5ddb504e3b989529724647ca274784ca978705e13dbabeafd8e5a4e31cdd1b31f857bb9d838dbc3ffd3ba421d0b5b4b11bc912308eb1f2b9f10972c7a444a5c225499bce123b5352144f976465daa444ed58c7389813480c61499ec9f1789c9361a7a4e1219f623c9eb01a238891eee1bcb2eb19cf32b8a73c46e8e7562edb2157f1d8cc0c51e6958f79cd87718f294fcb64d727c0fe3afea6aec6505a9d1bc99dfab04f17247e0e36acc2f45934c9cf8e0ac357d12b842ab64eebc8a534bc0a73c03a6bbde2323d0dcbc9ad78bbe722c8de4bda27c0e424b176419e88e7d4842afdee5fe35edc135eabb59d97ab945b85cb77d855e524d101beec66e2d4827c91abaa5116d7d0eb0aae214788770cd3d69c7f58b34a6bb3a448e4cdcc901b46abe5a7eceaf1351944f074a9055709e883fc7ffe0cfe4c626aad9c996f17293f70d1c81deea9a2614e8e367e415e9bf7d2e2d6d90cd4312b5afb27ad3a7b376b7a7957fddf9811bbadcdcfe27e5a74b68e24f01be8777de3591d81575fc4de362b53ee5a5480f0ad4fe3a12a535ccb645d694d8770c7c643e84b2e558b5d943cd67be9b507d50d139a567765a40a9f2d2e1435aa2aad13afb8fc9d0c4095cd6dc388b5d439c5cbe73945815c0b31c007cabc1cd1e05d24c29b9ec6a3746e7fe1157ad51ef450552973c8f52b310fc637f4f9ad8f12488d0cc1978e31782bac4af8c52ed78e5d7310b75f6d163abb7be59b5bfb716e914fa936635c8c3c4ae7fe155d832e6bbfaf4fe7dde2c11ddcbb8beea277fb414ca4cd568d77022a446ae832657a4cadd69ae56d8a1a710f83ceb5ba543ea508f1cc138d5d594b38f8e70275c1d50c7c3dbf8ec93170f3182237fe87d111f21375ad32a36e0f0699669ac9aec97842f04e59702506921b31c521563ec3e2793c39af06b07ee112b5bb07676952be6b1d8e7d8b8b447cd472d8343c83fbe6e7e2518f5c58cecec21ce704d932364abe4a83ff3b02860fe9bdafcfcd11a93e258ae79a7d78386a157a2c2ec763d33230717a0662b9269058c3c961813845e7f911b980071cc98d0ca7091fb9bccd9cd5358dd5ad6260d3bb8a77cc48a2ef4883f9ac0c652ad72ad2f7d4119bfa704408be65c032c8df18237b737152b7ff9f4ef88e8ed3e4b27e45c747ba7fe9de705a835538def32b2546b84c9912bba26d78feda730f71163f6f085c2b60f997c3736181282270d4b200dfb5ba85faf72bac3d46f7e55d629a1fd3359ca0ff6bd3b9c4febf2c92e602754b95fcd223136a3a1717ce4b6b6a3235b330d9551a53f58a6d709d968e0a6212dfd2a056ae652536645ff360b50ac3a75353e5c1a4d55aa7e54fb697d4d4efc5bf576c434f72d956db47e3454efae4959826f7698f2cb9c1bf5cf1a4d7f4d6d50712b3aadb8e7b43aaa05e50201a1800b9c68f5915011c97de3c11b95c991bbf12bd765c456a02831aeb5ea5adc3a894d5ca1c3ad7ed0e31f255ae91e3c9ddd7fbe72cf2ce4c6843aa86619382dd4762199fbaa7c8cd9eb94a297f9757a1289a918e83743dad34f94fcccf934d7e25b06397bc2b9fefce23b82f6d0d2c78379640f2c77ed7a08765b83672597fb08ac0db15dbb40ef12b95c5c1e55a0d2f201c9fd433ea5f8c077decf2271a0f2fd7ecc6c4157a9d329399ac731376351e8cadc3d029c9281badc6019a583fd2001303af320954b783c5aa9f41f88e58c33efb18513d2d156aaee52b6a461fd532984f4cc47c4ee4b48dc261625d476995fed23c04bb4786897a8f2ce143e064c3ae5f50252ca6a663d2e808848e8bc10602b7e72cc8351b118a37b80d95438cc8744d4260c48ab691312b7097b1415d0eba1712a3f429691be291a3b5a3cf5dc27a574ef1a2d6562ddfe09c22623e5a56d71c60249beaa8e5c5ec83dcd51f68021fa7cf7acb3a02db6dd4aca62af2a6a608fff9b9caeca4356d164b8b31db231b3196e178d5320c47ed2271c42916496e2998c9b94cdcd9a62b9ef19e23f867ea3af49a182fd6f1770d22d0d72b87763ae74abf18493ad7e72625a1ae7c1bfac5acd45a07952b2e6323516513816dac35c61b103ab7b9c32e222f36e6744f68612c20263f81374d4c33d0649db254623eea5aa162a91006aafed75003c8939b999245a8fcda3e4abe9db95ef1c8dd88c1fc3dcc7ee21bd027bc5a24f3c4e401cd2c8998cc97aec9f2b9f01a656af1b6d681fcda9616fcb56927eef02b13e92e1ab9856b0a1f5b0c858e56a738f9faec6c519abe4e99cd2a0657b3f354d5847f96b6b4f6f52e116a8f2ca59a40e50b5afdcff6f5f939f0dd190662609486a2e321ac601023df9f99299f08281365391bb44e259bdb869437e1ef1de36a9c5cc93bb2bea5a2685282dcd36301402ce8f884047968db4be8b9f705fcbbab9e3327c6d22230de55832a0653d674efd74916ad4f76656965855559cb5be0d2f13bd6ec454f4e2e9e99219fa4ef38d9d7fbe7b8d6d0808b1b0a829ef98fb48865118b7630cd409319c7ac1a7127adba4e4e71a29a98d021fd8adbf955d7ff62bd9ba4417aca311e4f39c5236972023c7549a74de89fb916c31c6391303a12d5c4bc5eb654e143facecfeca34525318a1897644c89aec56339c402c2ab15e7f1098824265069162c5f1919827768d5fedc2e4a6e1e1b254ae86f33b9e48519435515fec798f80e67ca1770ca4463eb008f4cf83b278864eb30ac2170787e64887c4727bd1280b68e099d5762531d9410e44895243629dfb38f964f39c58a0aa704913a2101f3666529ce81a5b0e6fe7f659bf1ef5f9f2ff4b2cdfec5b09a95012fa738b410dbe434892fc7c78b8a991958b0e9301eec8add3506cad507f0a0673e264f4941e268b57c8b5db88bc5eabf527abdc8a63a1457d342f48d4d847c69629228f2ab805dd9763cdcf13b386ec8e55bb3d2e13c3949e48f8f972d83da5361a481a5f2cfac7a6a1d2e0fd335ae63779519a069469aecf293780d7aa636423d4a85370cc1f5abd9f236c42768400aab307c40aca822b21616ecd2f0f7646c44df8c75ca5487381cb120d78f5904afbef6317887ff3fbd09437513ea28fe14b7160f85558b188758b99f5cc4ef1f59a89b30671ce3706c71b188495ca5b8e874d93bd104c2a09395d5d43a28b3493b6176a6dc4a2ced08c7a8fa33cbf0eedc8e9fc1bde910db22f7487e3a314179865cbd8af806c5ad60639b39efee75669732be0e8fc7d609cdf040bcdd6b110401c3e72e1a51b2f92086d475619307faac7b1bf661f89464b99363801dfb120ee850bf6800294329111f749d1fd339ad2f6fd64e6000bedcf7c4d52beeaa6522d9c0dcf572d6170bdc5913de42254f6d7b1efd18b44c33d264977603d762fadc6c91362c089f7166b221d6d0a92d7abd161231825363a3e5bb7372e0e745ab37277472894c5613064d4b1715c43c7612287c402ed777235538363d5d944d4dc574370d866ab6e05fa94de893be5e3bde3156d48d0a93fbfb2cc5e7c383f00581df899919229b9887cfb414f21c4b717f14b972c6302a060a06c09446ad1d07f027258ad5c4ea9e1beaaf632fa7d9c53474e13126b1b3633a0267b2d3b97dee9a229f9c9e2ab2e6648a40b73458b8a76320f75464d91b9dfc4e278ba7834af0e7c8e5b87f69317ce7e68932ba0f6d4303e5710289a7d57518cd4cabab3658f8fc6665a3df9c6c8ce5a45b4eb0758e93bb4747c863c47c8e73709effe5e2726249075c3562f98c7491363d0d56f3e97dbc4971a5461c7c1fa2aad0634909426971da440bc43906775d30be1d1015ba9fdad99962259dc762988aa54d7649c05a07bfd959a2f66a95400d2c83e35504429f3b27c85732d76b1d4a5bd057ef1a3c98b90e23a6a5c93d16a1f8806355f47a4ee63c1aba4cc4442d6f1bc280a23b9735783c6dbde2e110230f8d0c9527988111889cb62197515d23020c89a59da9fe37143fa73628ae0165423d31511e611588fe86c2e34ec6a80c4c83935e3963dc568d0fe7e78a3581e5c23f7995763c6bc177d5367dd67ab8c4d421968bbbe99e9db50c935f44d562d296e7d0ab2bc700c7cfb29b717fda1a658667aec89d962ad6934bfb1a01e4bb9c5dcfe287e362e42b53d3e516af7c6d5a5875db52ee1ac4bbb6468323b9c0255b15179724b1ace366ce507d3c8cc7cfb474b97f463ad426c032d9ffb1fc660c2bda0a4f4e1fa0492b3b5baa6128ad61191876ef226a4546cc0ad8192af3a31bf058fc4aa8acc2f10abdf6279e7404581fce48973b531a6159b3a39d25d43d89474a5a8895a4c8fd5661f2981ed02ed06b3f09ae1499112bb4f69cf8d91986c200c8c1749a580b6765885272fbdea073b8d0cffba2cbdaa9989cce4de63e8701682596f12a318106558d50e7918b4b0075f7f57c4e3a15d2f5508d8f4533df4362a9c7c94dde72e0753cd8d5290fbf6045c40a751dafcbf177ddbf5d544ab3ac058f576ec7b4e1c138ce01fb8ed5000c5a345e0e166cc1e2dccdd7f7be9aacbbc4ab585d80dcafc00a38fb94219106cc99019d042c7d4096eb097f22b7e83d728b9e51d72020ae0ea3561fc66dee49e83d3d1dce1ed922df320c6fb10b48abe807f4ba5dd30840d2d76028bb8adee426cccb8323b9909e4eb1bae608ef11a07c373c106fd2fb7605558ac0f03a8c586d441b747d0ed59fb97b34bb2a53d3308a5c4ecd8478b1de2e4abe4fd7d7dadf90e8e96fb4ca2927789e2716f02db9311f9907e34dfabcc6d9192235ac46f1e4c2e935c434aeebf322b020977acec4442c23d0fc8c98e7a7e49eed0baf852dbb8d4db83e3af27c3fb9c4874b7d981d75955e3db9a03d2a77c09e3777b8c96dc79821e7a9d11839125683384e0e36312d93e9248dab76c0cd311eebb944a6330176c344eeddaeacf00db7c60a598659b5bba04b41e05c9ada3d18e6a2910778a78c5d3ab360796a4ca47c337ba3e296bd0efdf8751c848f6dc070dee6b70cc7bbdcf4944b458855bd3f27473645ad846d679309d93dc9d988c7898df8baa560877d0c3e3404918d4dcf181cf0cbe0b24530ceda45e2d8827cd1105eadcce56ed19d8d477595a5ad8579c65a44d0a4feaaef529ca17bfcbc4716661e7effca607e235aca1a2cf4cc13257d3a947cf182c27a5ae47e1e9b9d05ab23474c32cbb7b435e993066765a1d03a1c87398e604c5382c10117b7f839382d68757c75b41a5b26c4213ea41a23582ccfb704162e1a2c24a048b50e97c7582bcb2c881325453d2762e6346168562306709cca39019ee3a29130361a0798559987e0e48810b48c8d4209b12d6f751d1eaf3082b9f0aacf8c2a631d7a3b2762d68478a82669b0da568d3747a9f0b5618bfe7289af866d7b3dc314bc2369112adfb70c932f8c0e479d639cc85e98af2c5255b659a4ae3affe81f2591c29a6099ebe044cf6d3701fb9b748e1f3a2788742e6fe98e922d1c16f0cc870f3da78fb924c9a0f1c59a68746d27e7e76276d976f436cdda5b19b00ee1b1d87a38732d1c276bf6f7ed9cbe3ac71ce8e7b6b06a9111568d690c181cd40eadc27dfea522935c95c3cc969881d1c4ffc0bb589485d7c0cc106be10cf7f446651ebdeee8b020f9edc3c4c02c55f2c3c949f2f9d22dda31c52d78dcd86be1494adffdbf99ebda46a4352a0b1c62e41102c9cf78c53628781ac3a80ca918bad856005ac745cbf7666688d5aa2a115db1e9424f2e1dba919e21b981ea591962cd83f375a9054716e4cb8dc01fc3faaed53cf2d0776e8e64f588e3fcdc0cbbb03c7ebc0a9156b009eea6597b0b1a830ad36b6256212e1a51c70168ce89b9922b689007e1894c8ce3847d94dc30315164a86a6191548fc12bf6a227b12a7ffb68916716285f1d11223f20003ae1108b92c949085b5c0a6b663ff99bd1db2d15e1b691a2d22c04fb09304fd0cafa9e638c58cbacc85583a9a5cdb89f03e5c60015379188aa85edf844a4d945639565b83c60168caf396bfa4a1da40d8cca90ec393c086708403f708c17eb9d134591731ce6ceccd43a8456b70de52dfbaecc7bea0a8b5a8e2173b330c73652be4cf7f11d2e9c8eae877bec0a0cef6ee332a2127747d4c08b58e37e1a8b170c1512bc033b25456e59548824d3ecbd05ede851fce9e01bb89d26e44afb68f9aeb1ca0b3c80fab4bff69c639c3cbab000eae54fc2923f93d53d6b77c2626282dc4293e718c7ab8605eae48ddfcedb00e7ea9d18a07bdd2e3c94bd0ea3dc52e481112a7cc41a55ed12237839a14104928bdacfd8921a83a67be65aed98885af838c6e16d4e5130246e1a7b4d1d4b676875ff6a0a9703558b90ac0d8a33a74ddcc85d8bf91e2c2c45df85057223ddc78f1f20a615bd5264c4d475bf16f1dc1b72eb210cb28bc45a4e7518d4a1507e42bc7c697a9a58619abdb7a0f99460886f99481f118c633420ce5f2941f2623d1cb7d74a927bc8452a99958ee1aa4a3cb0fc59fc23a25a593a394914d16bdfa295f13b1a6c5fcece12755c5232390543b898387e15fa45d761110bc6d1dfdfa4cf3b631986b75d3472d7f434cce0a06ad22adcada9334ee38975b27ccb94d96e1ab17c44285e2666f1c9107fddb568af94f469608afddb5313849d5abe3b31513ebdb040f8b24efc340dfaf26a9fa54f46bdd1ddab8855f85b423d868d8b1535dcb09626fa97749f1bab77c0ce981dd51bc578f1e1cd12cf02f8cfcb156bfb78ff2c236d118a6f6dc2f13cfdfd81a603a69661b7166095c1d2bb44ace2e2e2de4b8d488ed46773f34ae7912d9bfcca10c385b99c63c5832cbc562432b3a2c1a5b01c0cbde7ebf01a91195d8331fc1a9ef0b4e2f78daa43ac6538dea201d8c65d6aace8e70579626d7a23b993cfb4ebaf1b6b15dbb4a37d4b4430b1a16759fbbd5f870ec74675256e8f53f1b92a0e31f2959919b2b9f409ad3d6bc177b7f80f33d2434771072d1a39cc7c7b2dd231c53d353b318558ef1ddd6d7c0655626ef03251c663d320fe478bdc39baa6d7371d44cfa63da6fac25bc6ca9ec0cc69e928eb6b449d9cbee444929bf5c3c4046ccd5ea778315b623769ed013ce29129d25c93e5c18716e85e779656f5e301655852d9020bfeae2602a186fde8e39a22f75887cb771ff2640964f93507d9f33729ae9c6869ec7973b6388bf5f95520c8ab48ace1140c03eb3326f666689dae0735e99e229f59902f6a96b5682d2b77dd1c49893c99373d8b51bc19c23d21f979cccdc7b89d47bb176871a911bb8643db7b322a0689687a869f939b3e3ba313e3c664ddd47815e68c6f5515541313d1d8cffbd2c1e80e4d032ed0ef672c55384cab77717693e2caca062155e831370fce1312440d0bdaf55e826fe875ef8c89c40e022cf792160ce186a15e4518393717eef49ac39cb5de6729bee606a576517253de26d85ea9438d2146155e853b5515e8c9df393d4d644f4d954f7241f540df2b97d27448fc6436f53db1b12fcc82f08e7baa58b1a000f115cdad83bb230bf94d506777e9690c22f7e938b9c99fd1bf273c32e1d1dd26f8e6e77077f3213c66a1d285177eba5844ef8f13c90d8a9766557b9e9fc96e62db7908776c7f050fdb84cb8f4684c8ef2ea5eb34b8432da06d847c9f933ed71d44ef26bdcb96bf5199b8a448c40e0f92a789b1b472790831957a2ecc6529100e50c7d4e3dfd1cb85da3e46eee31dc5c717eb0a833ff62e13cbd52b61cda535c6822c0762a35760c9a202516c11a26b38daaa6f1975c57c31c3ca6c28a3999529b77b178b2472954657b5dcbc4988194d089b9080f50f12a3754f95cd016522b93b5ec78c74b99f9be33233d66df6f8e36bba968ca00a8c36cde89bdcc26a314c5d8b190434a78606e0a741bf526118dc01ac58677d54987cdb31562c734d867aedb3b85fb31afdfdcb61e39c28f268106d3623c0320fc6ab2382b187988a975f091c78659c9d89319392306f5cacdc384a255f372390e1780ab1af1a9f12781260f56ab942c63233abe226f4608646df3f97dcd1ba7131d8c7710c3a6f6d7f9f2ba7290c6a4f3abc40df7dc22a0c076d22b0695696085a5aa44ce6fab59b59373cbb098ec48853e97e7c61a7c68be3e3b18ef5ae7883a43b5d87679e58352e461e3248d8709debfc1c51352f4feb689ad137b92d2ac4c24585a2919893d2cffb325a4e3eba24cf4fbc4bc4b2c0529833cbe184ccc06a38fb958b4aaeeda3cfd012507de79e2e8a3237283374eec801fc9de35ad3d3458d539c7ca15d59419eb689909fa6342a338bb7c0ccd873e5721766720b7245daf878f9d6003f9cef63a436954eee451f802756f72db98f4fd3aaec57b8496b7dab3c6b4e1c9d9d85493332e47e6297bc21f1092b8a72617977ba8ee83a11e59c2037f6597a318675d63559ee70d528534d33fa2635de26e6dabe09f128b10ec7fb7a19dbff2391c2416c9dfa40826c72491669c49846739267e126f4245695323a5cac330bc2514ef0b48e900727c421cc331b6333e9efdca461663616d844a29640e2281dc7eda2f0dcb8685144abfb522eff29d971f95d40d662dafc02febda850e122697feb083c69a1c22b2342f09d5ee8ed92a90a1dbb2073e2a755983c31214ed44d4a12295353e11ab40cfd739a70ef2d0458ffc9d71c5a8500ba3f1fd2bd393d3b1b55f30a30e14837eaff97bc5a719f102fca06fc1cabfc697c9c727042ac32db34b36f52e33c9cbadd9848ae418bc195fa457f38fff6a44902ac3696030eaf15aacc755a277eef5a4ef26c820531a637c995fc9277f91ce2e411f734b17c0bb129433e4cfa3ac59305f908a84e33fba2d5f0b477b158115c097763a56038837cd37378785191889d932db7746cc16e14abd267445b84e22bdeda4fac5716e46fd05addaaadd05b0ee0ce2d0761362c08af13c89f9f9080d75c92b1f8d067f84b77b927f99bda468d8f13e9ba5045fb38386f1f2d8e8c8b52e69966f6cdc8ae80ff3727078f4f4f975b47aaf0515fefdf6c1ca0b555cb4f78c76f7101e6c4d5e3819c2de895b705ce63a3b18a40e8d5012cb017265f9b9a2aeaa7a6c07a4e267a6e2320e2ce2e0b0b443a01d4c7c4723e1f1122bf9d922c4a38f1cfb7108f8416e25f57ca12e7c9432ba9b357015436117896fbd8b148df20ffcb07d60df1365db3d1607986553017e48bec7979983b2d13c358979e1b3bdcaacfdeb061b1a0402473e0bdd7229cb38f46435a231668ba49fcaeb8e57c0fc758845884ca1f06b7978e5d18a9124747852a0b4db3fb2605ac1919e8efa2912f906b75f2d780a54fb66c738891c7666688d495bb6056a5579bccdb8cb9a323709056e79f1e5f0a901bf8624085c8f1adc4dd0c42cdcfe21f4b8a114020d1c8e535f4d91708b0be2166159db3593ba133ee4bca6a659e57bec82577f35beed6cc3b9483032e9f7d6f587539be41df7b9a553093578b00764f0f744147e49bc5d4cb11b4204fd471c9ce9868b4b0563f5713748773af69c63d4e71c27f944a7271be96779ccd83c53b2382152fd393bd09adf409387b972056a731e4db41e3899809eb4d39c4ca97a76788464e2c3ca497b70dab468cab46acd7cbf09e238675a2a859712d78a2bd16901341fd2a30636cb4e4b29eef396994934767658a340eba1bd3b09457ffcd07d17b49214279eb9acb830c92ccc6b87f86765b56e1f8d0b754ac8c6b8015bb40a627fe1bf7fa08eef42e870d31ecef5862c7320c5f7193114323901b3d16e7180777c758f92a8ddb1fe999b78e0ac507962a2c363dd99bccf86147afc4dcf93922afa3289a5e1df402e7b438c5c98dd3d3443a0316bb82b372d06b568628211abe9300e4dcb040bc4383fca9a266d83260717bf4c58598e49e0ab57598fc8406d1e77dbc71c22d452cf7c886ffdaddb8ff4ae902dc2c42d750a108aeee2922d73951bec412c8869ab1cba62bb4bb889cfd7c8e5cc7f7ed22b19b004b93d080215b0ee05fa6a7fe1b80f52afee5530a73ba57efd2b3fcdc2c08df26acc688ca961bbf51298fe1f17198323e56bec8a9360c58dc07c0c20458371f58b13be8a2110dc4a2de34b469e209df6769bb92e7d21251ed5d8abeecde6d7b03b77b15628947b6649d70663b67b829e9e2022ce0a451fe4c16db4b5fab2c24d7928b6bcff72686e61c2f5b54d52255d75ecb48f9dac4d58a7368b58818198aef596e9803e5574c00d5175fb74be6ca2f2626ca37723729934ab76290e9695fd9b8f375623d34c4b20eb16b18b15c6892d7760f6d29b724add39414b97d680038d9b995258b2c424c80755359d51efcb36a077ad945cadd44a1bf680fae4b9d42a875385e748813ab961429f3e2d7e0716e473f25195ef651a2da2642be46cce50302b483ce4922d9bb18362c07e35304db2929f0778c132bcd43f019b9899fd1a07992de17a55aa64cd15c41308e3bc70494e1618f1c4c989824b29d62e5464e02e506a597932b1efc731b73657800a72be0a5713162250171720181557760093782d133be2dbd1133ad23b09940ffdcac4cac9b9f8b509619bad175d22769b40e9393741dbebf65c01a192a8fd162b7c8f4546f22abd8869ecbb6612a8bb9f5bba80d25251d82db44d1009eccafe3985362bd762c6b6211987dc3690bd3d2640bd7d8f10ed38ef7f167965859522c6a6cd538cc1d6ccc82e5b70484cf156f5526e66f354e67bd691f1e085aa6b8ccce962d04545f195cd44146c4ab9881716353cb707ca4ae15f1dcd882cfcbf4943be1161203def53aee7688153956e1f2abd16a7c36360a2b58fffd401775bfb95e36215e3bc6395e5933c41f5f0ff4a571c0bb8461a65dc29bca9819f914a388263c8383181228cfdba8e57e6226e5ac44c9722e654f60c0f878a4d84460c390409c1ea592af11703d312b0b53bc8b30926b07a765c2636222920864de61ed2ccb30f9c1f87891426ccbb3b8053d72775dbead54c9263cc819ee9c406aab969b89ed7dc2f1a7cb25811a521998150e0bc20fb691f243e74414b9a522444de7ce9a5937b2a0de8d1a22e04d9579b9f0f2c816eb07f8e034b9f6bb4bb760cc95ba34ffd1e618a3b573886e5b35c85f9ea271739e16cb97ed2231dff4546f229b9d0dd7d959d845eed60f8f2f8142ace88769a9a2acb44571e3c9cef2c3e5dbb513c84578965818bf067651725f588d48f22fc7df398973ebd378c83d5514b1d223332f7adde7aec9f2d9a815b0daf48c71edc34b366348d16665867584fc88cee13433ab2be955196a017bebd215f0a57baa3c94d880491b9f365edfdd64bf6d49abe144f732e991853aa1c323859bb18817951bf99cedc3b5b66322da560ef293a7685c9c63e50f8758cc313dcd9b64252566d47f7c1c128942ff601e8c6f2d5578ddaf14f342ab3194b5ac521be1363505a9f4f7f7b8bf9e59103e5b5400f5a2123871f388da1d18e655847904660768507fc6abf1b474b166661a8267e562a0a6097fafba4c7907e740c5adc5434b4b90681f8d2708e83e1cdcaecfad5ca9ff1fb32a3aaf3696c7f5cc1725dc45653e7f675dbb0697e9095f9b71b3dae5db614ef79a0bd2bf9899299fa0c5cdfc463e675b022cdb88b6fa81be3ac03aeba291db26272aa66614378371f07bdd5318412b504edff6ddc06fc644e2d0aafd18d9b817f7f06b521ab1c45583e57ddbcb712e9807e178d116659a414caf7a17ac16e62374a40a5ff6f5c179625f17022b454170a5e2ded41ea4bd6c4226c7be921af01897e6707b2d43ced495f4d507e94b84b89ed12c183fc43788a8c455daf106c552d3d3bd766389a15d2fe05172b5bf1b1586afa7a4c843ee29ed9afc373a600d6807ac336e29728b7bb232c5f4346f86d85515fe6771a1a873d160ff2815bef22dc3e4edcfe161fe1bb127b7906522a7e7225daddf39cb309c585484c5f57b3198ff9ed904b3ec262c1eccbb313e50f86065d2f4f530aa6e8b3b1eafda85812e2972937db43cc62e9d310d4b07eb3bfc0ef6c38fe47ebeb5a410016b0e60a8e9695e3ff32a122b2624cae73814c0b1c175fb31e4463dd7a9e9dab153d3c43a5af4bea671fbf592226dba77b1d6c6f414bbb9716c4ad382bf7a1588065a85b6da44e0998032d8aeda831edec5e8c1899d3333453d31ab1f89cd7c66a3c68145c570afda85be9cc8b9b8106e0bf3450a6f1fd34a769a93466764222e7d1d9caff4dd054db883be6b0001e0348758b9d726427e723173fd0a4aa0fa6450d6d6fa606282dcb4a408b3389bdef444af9ff994887ce704b98b75f5a7a420b664eb8d2b88e79eaab5774f951b08b0bee9e78b53dea522c1a74c6b697a8adddc561fc66df52d78606e8e6cf12e45f4e617d09be33efee5786869b1a8340f916f3ebc10181b25df9998242a3988ad53f3acc0eda155228d00ee2097e3b0ce15b9917b976dd73a1aab759ebb4999332b4394ea954c99c15db111844ede58df02de234b6ef62b413c6765b3db697a9ad7d7aa76eaaa15b2b806d4255934aa6a84fa463dd7891aed045ac876d1e2779a18fb1761358a67f8b2d6fea6a7d8dd016b076eabdaf4d37dc472d4811570dd7404f72dca838d5b8a32cf2e4a3e3d42258f0e09c4e7131244ae5b0a16361ec63dbe95b020e6e5661f8deda342f12601c9777691689c9880f8f26de8b7bcf9b7152a75c17d6274c1cbf0786029ac2725895adb48b97f58a064fd6d71b92ecb83da770159b2b86d44083eb68ec0d3f373114c80358901d494b270fdad6617cc58499600e0fc8438b9774eb628bd11e3847c4e4e718acb8478e5591a3767686c7d1abf42991cbff2422fd353ece656b2fadbdb72579dbcbb998088990a6731cfcd15391313e56e2e501ea992efbaa7c9bd7507702f2b2d708633b9813133d2e5665dda4200be728c9547d3d6c26aedd3974f08e5e03e276f0657627e40992833b4d81a608476557f9f8b9d78ce4c4e929b33d62b4bb39bbb97746f7737d6de774ec42cab70f90d31ee0fc6c5caa73537607b7b5ebcc6c560ba7db43c4ae3f31c8d9f8f0a37b50d2f6a6edf4032593736d6382a68faec2fbc13b4b4185633d3a1320f96878707c9f76cd578c9315ea4ccc8806bd3215dbc69f8f40c848e08c501b3201cb351cb371c6245e554625eaccc79b94695dc132ebc0a7dddd311459fbbd5324cd767b05d47ddfff2ccaa7fbbfcf2b734513e704b46c2dc3c4ce78e2e5555dd4705f3663076bb3d3261ef96229fb20c939fd01878af761f1eaebbc10ac82bb6e176fb182ce50d24def1b654e1257267ef5b758584659375b3d5735e2e664e4b933b1f5f8a1f7a2fc1779eb962bd671e9cb90c836545161462d2f474b9bb8f37beedb9083f7964c96dacf9ce6077a5226602b37b432b61ef9e2c9f360bc697bcd3644c071b5d271e7a2d07e479876add410ce1cf323db13fc658c1c1bb58b040e3e7bcfbb66227862fdf8dfb6fa473e4f3b18f44e8f0209cb78ec009bb28ec67c9ef1bbd9cc86446d8b3efe21f0dcfe03ed7643412eb79ee3102abf171723f4b17cf29444f72f51ee20cf5494968b00ac3330c66f631f229eecfe79d87be4125b8eb720361c50edcc529104ef168b051e3796e0840aca9cd9826a61c581f1e8c535352e4018f6c2ce0c9c203afca487df1f2ed185ab80163226b94a9dcf2fef75a6199b596375f782cbc46ebe857dae6c58997378b8c4dea2a3c9abc5a594acfe80d5a74bed734284b52d618df28e4f7b0b296b6e1636344725f1f08e704f9eccc4c517923baae26bb0ae3b81577469eacc11172f53ee19e74c4a29a7d4b91c47f676df5a667d1c33901874786e2638e5b4d4a92db9796882c43cbf92bad762bf6c16a5c340e11453fd95163eb4a4d4cf9b5e40a7eb1205f6e5155c38e596067aeadbc0536851be116552b7c1256b699f106c3ef1217dc813f976e6d1d1456a3ccf22bd3c6acdc815e8d37895060f6263c98bb5199490bce2bbcb8c5ad12a12c8478239d63c9136da308b0d239a7cf2549ee254f21df34d3bbb931d8b02b372505b9e313f01a039159b07cddbf4c14136b7a90eb02373e8b01131351661381e38f78419a05e12d758d4857ad404f63266d6693f09eac116b8704e27c7f5fc8febe46a42df8fd9cb610502e8a236b311f9d5c1db92dd58a5d984c00fbf6e080f606aa6641f27b722bbf505589cca8e58a27c734bab2c72007744b9bb5d673b345f9648ddc43ee482b27d172ced2a44479645a8ac867c1c3eed47de6b78c1bc9b25a877930f6b31ac68c0cb973660616dc48e7c820ea182fd6723ace9c2c919ab15e7133cdf86e6e054df84be136f4748c4325ab4ad26a74d2324ceef42f151151abd023b611f7f855c0cd3e066bb975793f6f7c412ee3aef05af8abea2faf2715bd06b747d7e191254522dd210ebbb86c86db840df0bb7ce6ba5ec78a5317bea3c9f0695005d411b598d419ed2576fb22971331ab42208b06b2d22897ee10809ca5eb3b353b4bd4cdc946c2ec74ed38ff723c16a15771d05c85be13031e4fe00539e8b5a010d60bf3b16892463639c6c817e8fcdbe89a05aff2f6d1f2ed8909a22e7a25ccb8beb13bbb26bc90b1422c2d063b88fdfe400ce6d929c9f0b991e243e1d522de21566ee1fc408f6cc46437991aa8767b8badc27dd175caa281bef2f91e9ed0aaaa4541680d748d26d7ed432f9f324c19a9929fd1a43bdb8b58424495d0c4d4c1a8952aa65e99ce49a5bab40523ea0187e8f5e219d4589c6f72123666ae436049270b971974361ec448f7549166a192dfd1f72a03fc7ed983b077bb4b7a61944a7e4daec2b2d83a11c4f1259e889dbd87955bf0c8b2ed6de6e4b26e1e1f2f5fa6ef108666ac1d371378d380371ae6e5c8ed8b0a61d9dd59169b739c28758a936f10689db50e4766fd5efc9b1bd9de08e73637576e728c9347682cc91999706f7ef6c6da1430d95518b7d35a5a24e2ed2225af946f84556146dc0a58e9f4ac32b0d4290e854302e477f4b7b788593d13560dd7d8ba4bd7ea310b0a28c3bfb991ea148dc8758a97db0dc5cb57525b18d4ae73a51d1a88efeca2b17d463ad4e9eb3156d389559b93476b77e20e664ff651d8c2c9a80c2086ef3694f33098d0bf5a727fcf12f3797a62bce084d72573b2b4e335ab5a075e693789936259c77e49a1768c8b06fee3e345dab818f98a55183e616556cec41ff8abbc32066e96e3191b2ddf9c998500ba9756dd7dfcf0a2e0a2914f1328b0385e51e913e71fd5fcc12c4bb79bfd3efeec9c280f8f8bc11b3611f88c00cb7ecbab26ddfe6e6fdcc66b5aaad2b0b8502c0faa1065fc7f9b9fc3dd1b9f85b9ad1acf1323f8ee71625613e2d198d904ef2b6592336b60291a9f525145aed847bac2647fe374d7794213c0fc681526df59b6032e2cd3dcd9ebd9fe22ee5db91f0368907e4120fb7d3f9f2bf726e438190198d636527e36355dac4d582582c9a5bcdbd009e8b7ac66171e4d5ca91db7a850d48f8e94c7588699afb5bfcfe5af75a0bee3905b8adc115c29b2bbfbf8595a2c42e6e588c6de3a750fb13c735ddb88923f58ca87172dee7b69118aefc746e3fd991972378f73d36cefe6c63194902af4082813a10165ca14fad986ddc039b998ed108755c4064e0c0bc417cef158313b0b73b2d7a1dfa5e22ebcaa1df818ffeb998345939244aea50a6fd3043d33c01837b05d95a1d58c00667c9ca8999e8ef0daed78b8a413bb797c5e0c969ef99835275b64713f44fe4c6304ff18443866463fffc819fdc494f6717fc2cb65ec738ac4fc3c116f1e2c8fd1e77ccfef3724c05ee9fbd83da6c9f4a96d249ecfdd8031b94d78b4bb8ea1d0658ac7a20291c39b0a962ab921ae5e71d754e1af7fe439f1d8a9d906335a3c5aad23f05268a548092b374e8edb6437b071ac217733eece6e525c799b9a633f3449addcd3904eece41b62275a9a64c7f336617e76d3e515109882732ed79c1c513f3e561ee678cde5d4163a1efa06ad3f5987e3445039165e8d3c0cbba2dc467d7a3a4b30cb4306799a2101c61d0c243ce906f8ca0bc303e4194d035c99a95d7aa28ab069e9a28175ea99590de8e477e93b11fd90b31101b91b31b2dbc640572a93bc4b453407b609b0b645af5096442eff634ba54ab763d0b21665229f132d0c4f156dc1bc9226dc659af137036811336225d135bbf178e54e588c52e108b1aae334d9b5ac67e55b021fee2778a96ebfec227283827939f0744b162b89959d64a632c8ffca603540ef068e56e30bc7586c08ac801bc78578c7adb3d711454c91264a38ada88788e9fc38c80837b4637d227754b18f916f4e4e12458b0ae0ced7dca47709d93de41c35ceff3aa00fca87d4e09e25c530f3cad72955bcc45bfbc66e2c185c433ab4562abc37390999ac29d51d952632d7b60df32f5566b19b6b132e9f5b5a24b21617e08e3ff29cb8bd1cb1be5a1672a445b08e25924a4c8ab33797b186d4b227318e26de0fdc3790590f777de6eecf570c701ec68373b311eeaa91cf1100b51a230fa3030adff6ae36e362f091ab06559cefc5f187ab397f751d1e57d78a0202dc7778f218cb76740caf7d17f3c2e42479787e1e22d73d8d7e1d03eef5fbf000df9fbd2fe0df1dcf2fbd11f764acc7528718ece7b66386fe8dc67ca77eb752d2fd3ee71487daf5fb617d353b947fb4656d6c1dc0e10466b4d6e1f245028a62cfb23fb631c5bc5c04cecf955b47aaf02d2d8695bcd098543c6e12e362e4c6a76039260a5b3886c44065a3c6eea2ad98c3c5a3977def213c10b312d60eb1f25d5acdbee9bdd438566368c83a4a253f191f275fd9fe0a1e6630b86ab0dd8bd10b0ba06600186444fbaf8bb12b7d7355b764b9dbbb58143018754c35885b8387931a44a0ad1a2f13205d9898285f9b9d2db2eaf76058477059b90fbdeaf7c29218da87162a798ae5a58d3a07bd3b3c32045f4e4e94af703e18c701bbd3f829dc84fb96e4b78de2fb498075cc3d4536bba6ff316a081c96e0b13422046b59a6286285c8522fc734d32cbf498c6bf138f933b41a73893aef1b1a8033dc8b705c34ea8a9b31e152416f5ead787004556094672ee68d56cbe334a1756037d808b0d2a519f8e19c4d843c3c314136ed3c82fb3a5b6ea38f5bfd271ff1f5983e334b6473fe96b180a54fa1384fd77c726aaaa8f5294618bb7b8632a32525787071116c17e4896cab30bcc309a02cdd3c2949d42d2dc0ecb81578c8206bb3fa001e6caf93944fd2a47d953e5b974a7125f01eac4f753027b0e7de8e0165b06400e84e638863a00b8bda46d07d544647c8f75d34f2c9899a3fa6289d9385ab776328b1eccd9cac1b5b0775ec2a8c37cdf49bc4b8b1044bc65885e373621b3ff55e82b6c266cc2d7a02232ef73ed6c36a398cc79d13e44606abce049dd905a4c9fc937322de9c9b83452d87aebefb7213da81935cb916bb28794cdf18e3ca6dc02e323cbc13562df255b578aca38bcbc0e55b2a0aa6a4c85d7d96fe9cf4aafffc36b3209c25b00ecddfa01dd7f17c88a1f5989f8bc5e3e375a0f5a37e33c128d798dd58ff729197b80673bbd3186290f0cc47ef91a138333a02c71d62e4cb8e917f4c8266d25adc9fdc08359dc78b5cd5d0fc12fa709a8b69a6df241659d3da6f6282b290d80337c67cc34a857de40a8e2cdc82472ef51e6642ac87352b0b211cb3a0f77da34bc234327d8013274712504c4b478e5701ecae25998fb5b7f237621881e68b16a138792501400358f179d844e088531c5672a26c4425ee6656c53a5e8bf3316c6626a6115bda45af39cad766487ae5cfa7eb54884d5c708c17ebdd52442a97d98457b51734734de60262655334227d54185e23e6744af71e23a47318dc5c93b1cd230bc92ce1d35db2e039983d270d0f1343ff9218d6713bb57c77621c1efebd6346bc4bccc039374fd439c6c983b408bfc762947c2f4d33fd26b1a5855a07fb2811cfdbf92e49a84d5f8f45577a0faf5af3f3e04b8c86ebf3ce191b601fa08f178d8996ef4f4a921b76bd898776bd7e6d322fd94db0c8de80509af0dff6f531ce0de4f36046135d87a4c47a4ce9c8ac363d8787e7e523c23d551ee4cd03feccc1bfe1d2eacb87b851eba7fc39d1f518d6e173fe77c72bb86b7a86a8764e942fe9d23b7c8d03f311a1f87eb41abbb6bd849e078e5e5a04f146329d0c7112ee1e172bdf1e1d2e3f1e192abf9a108f3ebff7ae1c03fcc4048cb48b941fb1569b77b16830cdf09bcc022b95699eb942ed9c8096b9b998937a995c2baeb363f7cd45234ab88c47b79be80fed203fe3d2068895fc4483f9a45b2a120814a6f184bcda425966432c87332b035eae4972274df67397030543d90fb9bdd2324cbee81c2f5613d08cd0d4b53349768d93ea31d8394194da84cbfddc319a83b6bf95a230b8433d22fdcef57347266b44b677b1b280270da72630687916c2617a1afce95ebd3d2c08dff6f731aae0bb9598e29bd1cbe1cbbb9edd651c8dd1e04ec758e50562dc1f9905e347d7240cfebd65ab4b5a3064412ebcc85d3f4dcfa39498af876986df64165aadcc8fa811a1b175429350874157885bddbff5100671acc822549eeaeb6dfc4e58bbac0bbe1b17238f85d7c29683ecd772deec6eec7b0d0f10d0265a85cb93eca65d2eff6990df45762527c48bb57175c27746079765dd5ef426b6e54ce0a2db3ce863c46ea701b4f8b5e43e3ecb8d5f7987cfe0ca317b0c5f86fe3333e59e512a1c67a675a50d09de65a585e0f3e00a511954098bee328ec6c5e0df04584fd1b3f890c0593b2d0de69a2dbf6fdd5e49332671b7705a94b44eb188614fc034c36f3253afbcd02ba6fe42efbc4d783877d56fbb67dceaabf9101e6366641f23f7738e161d5a63d217f4ee97b089909f11ab59c11231996b70fbb5c66722e85ce31b10322e164f90ebd646e72207075c9a0df17910109ce4a4c6803238c6acc05dffc19d7b76e28e35fb61e6148f7aab70bc42d7c5a53cca2023134e07ffbceb7996cee19bb9b9a27c613e163258e9761babf057029e615392459a73823cccf58a97cbbe1fd8feb7f3d661f8840bcfb73c7fe958e28d64933271fbd454b97db45abecb85df4e715a2baf92b3bf6b66b96b32aadd52b0831688cdf30a30e14037cc6933d9152c63c5d9bbf21acfdcc93b6d97d29ae26c6f6656939244050dc84ffb791b27c067d8fd22401104741fb8a78a9c2769d5eb8a6c6e2efd885d8934fb183c65d0841f7205c02286f769f606f8e7aec340c3e7b02bd8700063c644e32972034ff3ce92b199eabfc8a5f269dfe59b932d7772e229077a9905f23d3dfc3e6e9b978dc019197237bdeeb23d17f5a9160abb55eea950910bdeb73b8ca339045833d2e513366af90edf3fc718adf5fcdcb377ff9ee7e09480cd2e4978d6af4cd4f897c3d4d9f95634ce600facc4780e6472cccad86dfa41fa5ab9112af99dad5abeb7ed250cecaaed65dea58c5c857e5cce631e8c33032ed1ce7eb01ea838897349a128496e8457c7cf69d88331733285c63c58fec025329da93bbcd4c1a0c552ced333e43eeed968089cb3abc8e73d3d553e691f25df6637f252b13f839ac3648ddc115edd3dd41cc684e25fb611b26954a87c935c6a8456624cd335a4ab74c6cab6a377c536cc18a9c2bb16a178e6c957f14877d9b03059171907b5d9a561bda6b959586a1526bf181a801f8dd911d4a70e484edc639918c758b97bcb73e8b5f985ae29d7606df8c83a8c1a15865334397e1c789989cfbb9804b4a79614228e00cbc5e0e272fa024daa45939344e5f020f9a34ee0af0b008b8188cee9ebf109f2c5793998c6edcc78178d192c4fa2991962192b4190eb77e15289a583f4e282e3e3f1f4e22251cd1b1357a382fa7b0316b9fd4d1621ed80450c676cd3bedf07b062576038b9f8c1766abcc86de376bf82fbafb6bccb64ddd4f881ef7d13f74c4c905bc644c90f740d237c8d4f5fe0381001c54f73b311c3ea0f5d796e394d98402ea1fa4a6a0c8f114bb10ac79b21cb44794769913507d193ebf678fb7d78a0fce94afa559d3d06e893402769e4a1f9b9a29ac18a135cf9bb7932f16eec587291599da2bff7a59921310662a7f890f5df6ff4093894006b6448db3af310f10603d6827cedb8fcb5e7aebb9c0bbbdc13e2b0c0294ebec60d79d52be0679abdb79871c7675a21fb2d2ac4dcd111f2c511a138654c62a8219ea30f709f728ac3935e8598cefa5a5d797e8115f05a5420ca2f955d3f48af6d45e7fd99432c9ac26a304f558b3b38a6c44d60171662ea6c720559428635e6f54caccb00cb2044681d8e0fed63e4dea00a380494e1619d6bf82afeb5a404a3ddd3448d59b0fc98372f7ead6a3158ff190cf82342f0556933cc8b5bd0e346072cf3a0b67566215207588b8bb5e3cab75e5fc0e2e759da82be535211c55af9bee5088caf87836906df62a60b12e7c1636aaa7c8a77bf8c8d5b19b2c875ec201caf96b7606ef9d6ae174d9b9626325d93e5b37c5ebf05580c167dbc213cb2e5ceb93908620066b7ccb079e09c20aaacc3e597dceebe2bdcc0cb952011187eef5b2a56fa1463c2c58946a0d9f4142c47abe53e7647075ca2f651df31a88d18a577ce7a58dfe880353c48596f1624df340b06022bb5f65c5f795dc316dca97c2bbc3cb24435b9a3c769711c6e8a5ddd42c6f49ab3933df311e6142f1acc8371821b9d0e3492590dd4330be744ec9c9e8eacf2663c56ded4750388d511b8d12b77efb18ec0e7bf2e7919accf40a709737a5498fcd4b70c33fd2a60c6d7c5c282a135e837354d945a85c923ec8e195b247dd5ae21333d6270566138c61a5be13598ee5d85ffe1181aef4ecec941c49454b1da50a7f86b96a7db61f586b2b4482c5b5a8cd937f2d899148ddb9d62e5464b957c8bcfdd230776d96baf5f3d21bbd9194db8cb2d059ba6a78b7acf5c51ca6effcdd0d4c364461a07771960e664cbed7691788be3309dc947e209c671a5d02a919db4a6eb27d86a4e157806fd2d55d8372ca8bd6374c749cebf737a8155b8fcc2314ebef6cc2bb8ebe22edd11dc496e99ad63ac7c8735ea8d658dd77218522af89e38c6e329f57291a76afa5923be702b1c62eb10d2cf472f11edffdb29213332e52e6e5871430356266e774f919bb9f692c177520a46abebae5fd1313f5762583db84fa67b1a1a8b9bb120aba9f36a1f26ebc64613daddbb58a4d024fb9e8e734683955ef98058cf3b333365734805fad060ea72c549cd5a3c92d28825e6a1789ddc25f95b9235f4f305170d4ae2ea314bafbaf0df1cf09e9b8b3872239b081838e95519ec7ffd01ab23f3646d7b02d99389ab303f7f1346f1f5b0744ff40af49c9b2dd6d3c47bf3d7d9f506813fab709c245659c295065537288318afc11d13e2955db458bc47e72e9c93da46190ac2af87452e87abaa0ad15392f1f48c348495b13aac49a0efd632ff722c5d5a22ea9859195bd06c002c7ecfd8281c092817cbc9edb92e2b5dea1a3c9ed48830f310bcfd6b86a4575310acde393313091c2332b8b90c5a73b244d9a424f99c3185c85d0e5a867c303ae7847a84e56c8093e19a824a705bf0325136361a877f9d006b88098e08c18f63a250fbc4d378f446955066c0728c55f65a86c9f78706424c4e6ab388293b73dd5447c36ae0195285821919d83533034b4db3f716b2f85578346e151247a9f0ba4e3134c0b8c6110669619a8ce76665c99dc462e65dcf18c2ec2c581183db4e4ce5541fefff1be0360bc2fbe9eb119eba0efd0cef89580e4b75adc8a6eb39c90ce6f704aadfaa3b24403d3f3a02bb38d563cd41dcaeeff4f3579e746e29f2d0003f5c302c1686e7d0bb9dbdeec9d9a0781634fdb15ae997322e7e768893afd844c88f4684c8d35334e8af69e9fa540cae20d87c104326258906fb68f90aab5aec7de18f956336d9ef68dc7c227019cc17168a652342f19e318aa1bf924469a3d77f332f475479e663f2f56a53cedd7da666c1766a9adccd8999fd7c7e5638d0c7b2ce5a86e12502ac79a94d78948180e59d1797c0657eaea8a7d77efb7bc4adaea416c12eab55189e8b5b090fcd6a3c68688d362f173367658875742d67b8f6d290ea609050b68e9007121a84b7a6f1fab959576b06791902903719b0b8309ee5652ed5b4e45a8c03ed59ebe0cc0ab1ce097267cb6b78e06a946a4dd64dadb419ae0bf2455a4791bace94ded044fadc215e3ec3ca09bc1377bdceb3fc00feeeaec1640ea6938bf4c3d080f61e80ccf03841d42d553ee3912d720c3d13399e51b91dd35c34a831ec260ef2ffe300ab633b332e711a1f2fdf9a93838986c9d67400f76e7a06c34786e27d2e7ed625eaea7767c9c50201c191f9b9da8cd9d9375eab75de5976cbc42356e1f22beb70f9b15d943c3a26120f5eaa7fe5b5d89c3c98cdcb91cd91cb852677c32f4bad4c76131be724713e904726d29ce2b0d590183ad8df68b6a02bbd2137e0c9e999228f1322af67f3044e69704bc750e7789162152e965b848af52343e52eb360797068a07c654e8ec85d5a8285863298aa3df8e79c2c14d8aab15f97afe5675ce2ebf56659fdda1786f3e4367de19e86889855b0d33d0feeeaf22c7a38c460b5ad5aeeb5089687f9fa2c55722b0174c3d86891e299a74c5f5878e3b55ae79de579d9e843ccea0c332c8718f9a27d5cd797e5543e894742aa3199bbe1a8966171fe86aeada030d90d6cdc8a9e932847aa70945cac33579216fe3f01eef62ec9da85f9086bd877fd070ebb849c28486ed46d9a55e77bb0b4736069abdbdcec36ffa929ade92cebdcf4747bf7649629e616f7e4227e494cec075653d53148dfaecd68bf1a97d090fad16301e0a2912dc11522a3e37526af56dc23972b8bbdf2b49181a58a5b54b5d62a69f94ff76b6a7107bb587c1f6eb4b1c43b742c2bcd4cd05a2d3f9c9c24775d8f261465db31257e15549ef972338dbb51a6597c0b183310d66aa2073e715696c8a741768a93438d697ada31939ca55aa6a660636029c67012e4e5be8f2719b9667f654998cc35dfdf3e23e3ec5dee4967efe6c33befcc9dbc9b544240c47fe7d70595e0cf0c4effd1d1a5d07f8e0eb4caf1f7d03afccbb7f2fc2373f35afb4f4d691bc19d5bb83106bf74f50edc46aeeefd131344b163b468b08b94fbc95df980c0f924ef60e90bb3db133bfdba1ec43a4adae8eb2a596db5cd2c489ea3f378c7265cbe60172d9b3d734594ba5699f10b605e8b4762ead077517edb10bebed81adca3d3d4a27ba2131cec704ff87eb0ebcb4036a3007f718ac0df381789efb197fe1e3bd1c13f07d03d8eaac23f55f4ba199aaedd69ccdf8a877c8ab4b67c2fc9257c6b6aaa58eb9e842e9396e18d1cde98f02f172981e542135e8db9612b4c6de76f15c0faaf434771c78c34043a27ca975992b7bf4fe726243304cb70bc9ad288e8b435ed357297032c9e54ac97145179f6eeb4b5e71e9a95fa7daf19493f3c3639fe87c717157edf33baf2f423394d3fde1bb3e2ec5d6e9ad3fff22740e289f51f571903611725af0577fa14b5cea2891f4aae548973827c766c943c466c92954925bb89974ad8ec0a3665c8fad7e96bd1770c0f94e7c9c5fb7e4282dc3b2d55ac09ae120931cb15d794d56d23aedaada7fbca79594125dfdee69d857f1210dd91def0d37d7c8f17e4fc7c8fbde967df42023ffa5b28b994bc2874e598ca5977a1977f89d6a9bfae7e52beea912d6a3cf2ba6e73e0d021fce5e01bb87d5e9e58bda848e4ec38825ed7da0bc064ddc41665e31f4be8a18f89c27e9aacdc3b4f1a3b610dda52e4ca1c9c9a26d289eddcab6342973156330d2e3f3f7658009e1b112c9f1fa992ffbfbdef808bead8feffffdefbbdf77ec97b79292f3d96543b28568a80152c8005b1f742ef65e9eeb2f42ea02228d862c3166b349a184b8c31a61801f5a5c7c414638b05d8bd33e73fe7eebd66451676974551e77c3ef3416177eedcb933df7bce9973bee723f6f304d3363ee919443fc57fdb86d0e3b661f403b6a18fb0df1d621ac8e681d1240f3529b9ffe83261b86fa130657c2a899e9cae993923b7ce73c96ee852be1b9e69d07c3c00ffebb7189ef4cd866767e5425b3c66f750426fcf14c1d32b9544a176890ca00314f025d31631019a7691aa369ba26dea035417496b635a5c2dbb9fdf8725d0f73d5464c3b8541231462d4c74576907e1e9995732bc125a022f30cde88986585e4590af82bf472eadb30e2bd1baccce15a6a3e9cbc61d1f55aae9c1005934b7bc0b6b5f9b9ba7756473fa1e9bc743bd03e941368747d9b58fe9cf71ef10fa499f107aa277087cccb4cd8d2ef1da025133b39079a95ea9b5f32b126689ec1891f4fd80852421d482beb6c455d02ff14df057af03dfe4f5e0863ecad64eb7c3c542824edb99f964bd63147cd11853a7a142121878392593eec47c43a3165b99a67be0e2bad158a0956937d7d8c6b9ce36510d369b405a8b6612db5c37fa32ed836dac2becf797fb4790aa010aba35bae4d2e3680a613f8a32618a5f81367c7c8ab0684aa6367656aec667c92e4defe21dc639777183223864aeafeb1c55267886959030af14bacf259e7ed13b186af10001b52e31afcf0cc0eaaa4ba311bfcf00eb2626577ba6d0b766e49222c5b2bad171cb353dd06c35e6e40c3723e61b86976aedc397d44d9895af554ccdd2a68e4b114a234ab4fd0b76d488cc0d730aeabacdc9af1d8961046c5e2fb1f9bcc87e5e65ffbf86f38a5a9d38c7c16cbe59b309825a670539e91a2fec4040b71460a956689d7c8a88ef6b73c4b4a8fda14b4864b40583871356c1a084954455b81d2695bccd99441f1a49db00a3c39782b2291e2943a75b6c235c181843bf5eb31f5e458e2663ae3956adf1f7506a56eb9b5fd67a4191d6fe7fc652c9c1954c4bf89a6df86dfa3e1ba6592d1d104d0fe3a640277a476fb88c6c10582945f4737c0d8f872d81de933284d299b95ab5ef026d44fe66adc3828ada8ed97be19f8636279a8f855b6b5f0b5b22cc9a9baf550f4fa027edc2e1572ba94e208ebbbec62597f992008e30c0bbc180ef23af34ba39f94d615cda5aad1d9e521a4a15419f53fee61b2fcc5fa91d18535e37617ab65014b2589b94b68104e3a182fcb9f1e910eb954adf7b759674cff390829904aadfd43ac81a9677414dff6e3a1efadbe6f1b679f6bf2d5e4d4c138a5f7ad3367e594d7b4bac2bff2261ce8c1c528c63ec174656676ed00eb4440c56c90e7874d91e1899b511e6ce7f13e255159c89e1a1100c4ec4eabc7e0bc17b5a1629eee86d5a7a0a2e72d4aeec22e87f8725d2f79136c498e294a84d7828b5e1c313b55ba4e3fc3b012be04ec0b20d2367d12c144146022ccf14b29c99334711b0585f37d9b87e58bc1b86233d2efe7df9dbf032334b8633336f8bbb8a2c1dadd6e64fc912668f4f1346bb29a1ef8434e8303907da232f166a5a724e1e6e8ac2cd37db30c0729f57407c472490f50e91f4203313cfb06b5cc0cdcdc648ac25d3591abf803c5add03e117a6b57c6b1b0ac791dd62423ac94a7a533b38756d9d3502a16c76e135f0bafe8be0f9114ae8305a053d46256b874c4c27dee3d349acbb926c9898494a98299581667695947e33350bfc266791350804a8d92260b1ef84252cd70ec0bfcf2daa79c527ffa61d9bb7da5b20a5df0cbc1430c8366449edd0f085b51d2cb1bee62c20fe9332c98a0e62256db22277a3d6beb9350971cd22e8b397d2b88c4d3055b51ae6e2e103dfcd0f8160bcd5bac3d0cf2e020eb04d56634a80a8bcd8f13b6e49b078c53e18698c2981605552027f1b188b350cc999ae465c53be8e7db870dc394a28d3efcf4d49b7f68fa4275f9b0d30248e9e1c9f4e37dda63dae071f8cb67f4da77d899a11f6d72b18ae61c59eb16aba735a26298d5c2a4c454737d2d4d437cfc43133205bbc135e5db04deb342d9b2c63d77d97695037914259d6b898697b15cb81cd5b40f2a24a885ffed69a971ba2d191c1aa60aba6574cb93029a4581be3aea2ef0e89252799b62ad674944bd4b3fe6b7b87c09584d530e0c0491d8f54c511e8b8742f0c928bbfe2b53d93b52921c5759ef8f7c0a21b2ffa32b31089fe7a04344cb7dc50480ad6709c9a55173725b366b025d6d7183549f550d243d8bf430414aeda07ed9a9bf180e9366bde813792d64074ca06703a7a0e1e698940542ead0dac00fe8aec093e0b21bc4f287c712b84c104473bfb6c8d7334548d4b83c8e5fbc0ca988583e61c96e272549052fb70fa7d3723010b81cd2952383a345658a2df9f4b1cdd6d1746ab51db18ae846df30a49b2fc26c680d569399037623ebc239abb92f31cfb639bb916fd3b4e0afac580187a68480c291b124f925d1320c4335918313503ec55cb6b5e46ad4b647660e366dac133053be08da959c2f4d16a503846c22a87707ac42e9c9eb28fa0879da26805039dec7905c2f8a85270c6382459a340e04a5d0d2fc4adacb31ea112dcd87d4c7361d71b1a47960e8da35b1c23e919761fe7d0a7c4c647e4d3c4ae3ae6d19b01c5e097c13628f6b5f6183c87070b8e51f01503de0b786fee4a6df69cbcbac9a286b5e0fa733373ea3af5645a20032cad098055eb95a2c91d9f52ebdeacb5c5e60a4b69b9c693e2a1b1b4d2360c2eb097931a4ba735d73f56ba1f3a94ec827ee91bc0336f1374c5d0060e580f81e089caf80c707553d1e36cf35ee9ec6d7a5105b6112e042f21c541a5baa86ca31cb115f0770c536026c23a040c6b7fe30a93a2c63138961ef0509205fafd39460a077b07936f70d34ec982a415efe8180f303e0779af9c15f0116e98fa610afa210698c623694835ecb35799d6f5f6944c5290b4521885b1440df1a6a3ff6bc98eda37fc179130663216c69613bfc4555a672c62d1d04955f95b37dbaad76a9dd9e7224724d2779d14e42c1b0716b8104329ba48bebcfa7e31bc6f3c659bb580ac092b8130b93f24fc9bbb80ac1f1803275e65dae5e03861d1b854cd5c51b38985ffb827d4bcd223102e31cdb9ce3ac068c0d2b8cfd756b8cf17a6350bb098e9cae6ec69a6556def1706978725d04f872582bf25d66dc156185cb80dc6ee3b01ed78aee04324092bc16d6c3251f70c86dfac25fe72538eea7b31338569169f24ac8211f14dc45cdda661e5c12373726adaf70da15b7b05d3ebc60096c81dc53ec33490fd8e91da5cc94e130346fb860ac76d43e9574c43393721032696efd5050e665640c7cc8d309681d0b7a80936c48f6eade71f93d273049b202c094f7fb0655a13bbbfbd036248b16b0249f15e207829966a6c57edab6927faa0d09752018f071681d5bc02b08f2d872e714c832ad10533fe0d93c73337697acf61df9b904efcfb4742393341b73844d00f31fda66710bdc2c640317c44be7e43611332a80e8a81aa91f3610556c6c6f823d454135783f79058588bdae5d814b2d47721110b2d78a9ae3e3536a5a6bd4d10bd88a114c6a655b1a66166f72e8730614eb39ce2fbe0f1956f8375af2038c0fafc636a36593d3d07c635ab4f36a715fb2e3d5eb647d3bdec1d4d6f24ea6bad943a5c5a40625742dc98645a21f9494ca243c1b7be4324fd89693c7bb0828e29955ba665fff2cfc9c957dfe81d4277b24d5b6b6da4198a1bd9265078a757a0365bf603b1f6bfac8f93480ee791443f1fa50647d93c50bf090eea3510c7b4964ba672cf77f6f933148101cc2fcc64ab625a546e7499307df5bb9a9e4d91e5a1398411f5d99b84f1731690bcf169741b725731f3ac560e91305433d150ea0edbfc3798c67260df27d019350bd4e2361e057bd758c87d8301d6f46c521e55064178fdc9e9579e9c9872b32d9b9bdf6d024513d3d8e7abb50910deb50924becd3cc57bba7437385b07c2871dbde15a7829498b580a439be76f3df7c8e643175ed87eb0e6955d1f58e61493cbfde0bb7a175e5aff2ef4b40d876af47f743521054566c9c44d37398b2c601b64a65876dd84603d3cd6562cbdd9c6368c6ee9cd342c2b23352cfc5ccf40eddebe417559b20f0e4fcd06c4c02977151c8c590e8931abe1758cd8df7f9a99446a086580fa059698379598ef96e6a53b05d574f5811b76e1f0ad670a5926714f3dd2d83d622a50621974f72924a54e0afa29ebeb32521aa3f9a77f5a67129b03fb3e7b5e5fc5b1fb8c5ba5ab508de6e798149831623e9c9a9026944fcdd446e1ef314e2dacf8e64b4c93bb80716dc61ca44827851ac708ed8efe519ad9cd7a192e874e312b209e01f4d90e73e152ce66014ff4ba9adb9fc40df6b76d472e3c2626e773cdeae1118c955af32e383153e1220302ad29d1dbd6017fc619f917417cd176186eeaf511b030699701d626a6655d3305b07ae9011648803538162a47a7c0bebc2de08b25af103c99b9f0bc6b02243a44d15f90f6b89b5f3378d79906ca3418048b6ba392e8a68c0a8d5f53047418daa12883deb3f3e946769fa27f0d5b679f66548e9e276a59e7a2cb4821f67dcbc44e8371ac1d1b9f22948f4fd546cb808573ac0f58d6460296b342bbcd59a169163d4bf472b08a5c4ab2d81afb9a99ab178b776a5d97ec80379a035878f0b1e3c4f94739ddf14322a0abd8fca847124461ae20d31a04534bb07714e369e0fb2999f4ed0959e6d51444df0be603da8591927ea1e45b2b934c42fa4e4fc924c444dd69d9f0cf914ab2614c0a49fae0243c8b0086a77a696b4131340ef6981ab1afcf4dc5e606b9e1b55ea9744bc062928c4e64738a96aedc0f1d56ec0527667e1f7554e80acf76f535cd0ceffe27ffbbc63682fe3e5a0ddec83386fde76f81ce85db60764c39498958a673caa349382ae5a24926a194905d33334f9b3e33571861b606cf5e246e4a70616bacd2318a9eb30ba795a871e2210fdf855c8cf72b94888ee097872b49ea80187a9a2d4e934ab0236074f6066acf4cc999b9a46c7a8179053cd1f78469350e11a4d03e9c9c3645c36226e4bebea13aa7bb0c58cc4c2b9e9001e198b88d8085a91fe1a52473500c1cea30cf34c0d28bfabec1b4835ffb8440e5f8749217b898f86e455eaf03a6476963ecd16a66828f4d21cb07c5d0bd3601f003bb9f9bec1a8229daadf4592da6298d4d86885507746661e16e788d69ba9e4cf38a8928d5f99e10b0bc5435ed744e775a6b3460f9c1cdb90bb47158ecd46cc062662a33d14763e6837334fd7280821e45a0e765b6b89806146be169d56a887354c06151f330c3a7831a8773346cd87418fa61847c73c6333c411bed12afdd25d3ac34b559f173036284f787cf170af1fb087ac8de90bb59f0c8ddac11399010b0e616421bd7445ac534c15f4d29352f3bdb11e4dc94f4d38999a4f8cd7d608b943416f11d32cd23753d744b5e4b8230f6ab5730fda3a34420688a9685cf6e4c32ac4ddfa0d3a6c4f08d6a7821babcd6357269cd20fc1d038ca73d55b5afda04c2e51ea685355c8b63fd2494d57634f73ed13fca342c058ed33d89eef75d480af8eee3629220e546481974f44a83f5fdc2e08ca91ce612e300655acb176c33645458a04acb8434cd2ccf14a158a660362689d82952f860689c502c696aff8ba095bb496393b555679eaede032f2896415ffb707abe5710fc618cd965e57f2ba480f40d85effa47c2c7a393886a72264c437f1f3a792d65926301d1a435da81ae8924d3299a56b0eb9d6780725d3f97b2a9b1a249392c91bee7534016619f686ae1a961dcb2ba6e485e88d79a967dedd90969b51d6e058e1ad12f663ab0393b9fb8526313bff2a6d9aca018d0e9ae843ccc3cc062b0114b2182ef402e26c98ea3f052d06218c24c9c4b720a8e29808541a54cf310424bc8c2b052186f8931252caf19105aacf1eb6444fea21cfbd53f829c18142d2cd7ef47ae2f88ff5efe36f451ae84a932d794514c13be3a30469654f724d89eb80ae6a3f6d89226cce663d046bd1a1cc725d3f76d43e15c87b9b7252037a9053a45d1ef31e017ef1da4135a7446cb0ee9d999375e9c99576785a5cd90e3beb17ee52c026476e81f494f55ec83c7971f303f41397219497157d1ed2fcf0418970caa25bb9a17cec0e52194f052f09c9a4dd40c186a4d2d162a6d240d26fb26af87b1a96ba087454cd4e5352f0716691d7a06d1dfba07d06bdd1a4a7ed667e944c08a24d5039966a29ffc8c274872ec5544194cf12e2439b263bbd1fbf2d34597f70e81cbf611f4cc4825c99e92051398d9dc531f085a4ae3c5349d4999309a69736923e6d3ed583507b5bcc6c24c6e05ed06c135a6299f5f7b04ba6e3e042fd49f070ccc9d93a7e98b7d6270aaa179950372d1341d1c470f4f4c27650cf4fe6ece491c023ca642b9ab61d7e058f88c81e5458f2498b174af8e9e9a0b17a3c57711c44ccaa01bd1af602a7d0c6e104c59e91b0217767d041dd866b308c52d8606cccdbcd9c63e9c7cd53398fede05cdb226008b01cbd7ce8adbe965f4c5a70822a765d34dc6d0e4749538d49956f1e3b0047a60e9dbda01c6726859c844fc1ff4b9a9566b8725ae22f3d906bf8689d4326d4d53cf04a963d6be0f43d71e804ef5fb96e965d87cd65935f22290c35430f5c743452b14cbb4b1e6de0f82d5b12fe1dfce0aa87652c039a728f87e840a861db59049cde521102cc8b9fe00747388046416f8bdab9f6925ad500bc14d3d340e3e9c9e4356e12999a5ea0b8a91ea25f0684409510c8da73bbbf951b6812865da16ed1148897ec3dfb1bf419f5072c1365c38ecb7f8ca9332819f680eb18d8fcc132ef164c58068fa8d44574391a7bda18673601f013f8e49a6072665c0f8b905d0ab6c1b3c76b7cbbd8bbcf66fc2bf31a5c7b75098eda92605fda3e017f682104ff67a04b0fb979bde7c74f1a5b4930fd5469692ec886530ba7ebf737235363373ebbcacfda9c6ba81f9949b953f059b207ad3399a7ceb9526cc29d8546b76ac54e15bd065d10e7067ebe552af6038cb34dd85bec5d08d338072315a2a0ec133481fd327043e33f5185dff546aa492ee0b5d727bd2b145c6c7cc8f85db8569cc1c5b8b80d5dd0060b186bf67e61bb9dc2f4cf8100b5488dcee92200b2756fb6160b5d13e92fe26dd67a380c5b480af67e6d2ad0c2c7a62c8c2bd7e56cbf66a9dbc0b40c1cca91fd88617833d0d02960fa51dbda910bc982c0b2a8629f5fb9a99a3e9333ba76e4a53808573de2b985e734d24a727a60b630e34631e0a7642af82ed30ed95597083cd7d756c39498a5a6e7eb028978750a6e4c2e0a9b9740b039f8b1dbd4d2fa18e047548c3323a051458faab454015e0afb30aaf3d3325ed5a170fe5756bb7c4ebdd3de2ae5be3bff5db18f6fb5189d7ad3c12fee8c8b4b3bf7be9f959f0df5840010b2b8c88bbde6318fb7e43cd35fa7a776c5eeaeb56b3726fb64533065a9106805434abf6c2b39eaa3f3a0d8dbade439c87061adec350769ff372ffe8802782f5fb51945d780c0b7be05c60ab3f97723fc3636ed878aaaf754da9b8f95253e9464d688a7f19ad86508f24388e7eb37ea1b07bdb5178dd9c405b2e0fab767500fe35311dc68c49a1efb14574c5d4b410e924b1ce360c7e1b930c01b8005bc82cfacbccfccb4f4c4ebff2aa27031c37bde6a9d7c628ffe8e0a1bcfafad8f8cbed915a459f5709016ba60afecf3dbaa6dd88d83f3ab8d5eb476e4311d0d8dfbd94b5afcfccba2e3278b6262e25d43897ed81a746ab2ebf3c38dcf07de03d60f3cea8693727efea1d94d4686a872dbbfa14827b63f3318cfddd5375e5d5ec55d79e35970954acd0b3039e1e9e08f307c54255df10f89999b56bdf3a0e6d0f1c68b902ba5c1e3059fd3e38ba2741c62dfa617fd3d3537a06c185e9b9f4ad19393ae2382e5cea8b44573cc5361c766111d8d9f96495ff22cb705f71798824bc04825d13606d171fe3627c1a8abdea1504df072e26d941c5607337c78e5a57fe667861c136e8a85e0736eaf5d01da3c48d69d9f8738bd4a4df65548095d83641d79c4dd0098fe0e5d8add628983c8d456853d782358ebba17bcc36663ea479c03ee4795cf5013c6b29530d9f13f2d1fb14413603ace34843edb71862a3cac18def402e26c9840cd83330064e614c92951909c01816d03b044e15ef02cf457bef5e355d39339f819553fe36989db416e253d68222730304cb2d65dd9d8d6dca106c59d2cffa2d6d2384666d8180dc2d30efc819780cfd57adf5d9a1f95df13ed8b1fb8a4c5b0fe1f5ef459e87349c0bd692d74388dce4f9c0bf656ed2cd0bde7b329b47359bc78a43d077dd1178d112e344e09f9a091d9939f845bf501dab6bd66670d871c4bc3c532e0fa3efea103cb3e630bcea100edff70e868ba656309609e3b0f041ff28d8bfe23de88a6affddd6b08ab781cdc2edc228cf14badb351ef6f70d830fec23e083fe910d37c7c65a141cc5c6e6e40816dc885f0193125740afd6f6ec10a83196c92b15423c92c832a6b57ce810c1c66ee0befa37d1ecc3e1033b6cac8f69d9b02a780949dbfa21bc5c61216ae142f68c7c0b610a5b2f17d9384f0f9b0f1fa82aa01dfa4ff94ee462942058ad3f00b66c1111d6a8a9a6a09c68cc16fccf83a261331e75df0bc2b492bdd069e96eeda081d1f49b9e41f402fa47f4cb5a99d3f0fbcc6cd1469593ecc83218d3ea4cc12fe11fc8e0ea9a48563a2be837c8d52ed71d34a7613e1f36ecc74d45f7cd5d400a767e014f5aca19ceb4600f6602ce6f3b1d80bd108efb1491726f5e728b8b2912580c9e414b484647a94a8ca9a6a0cc283a5a4df74cca2049780a742f4ed2309f0d833927a4431faf3498332e956eee1100bf8bd4c552c2b495bf694d8a10a73641f0cb903858b370078c28b4101b832564ee02b09e9d4ff2fb06c3594ca3d2e77a37a5e1fc60184baf607ad531921e9d910db1b31640bf59b9d056e4a36f66280702eb61067cae09b092bdd4beb0f6a73706c79005395ba0bfca4281c55c1e12612afa4cbf22b20c351173182e311a1e17fb8474ba69ce02127dafef07f3ee2297c220ff856449af60f8a5b35e7519935944a5c3073c50708e867d453b616ade1ee32a55df0d99990bb6d3f3e8e69e41f033be70cc652745ff233ec37e61f4926b3cdd9355214cc8de6299942ad174fd16fe0f2b120d50c06e660afecc34e03f86c693a43d9fc0eb3cb29d8b71be2b66b6ed3e066d0629c852db30fa7b373ff336b5ac61052c84d0f40dda21ade91ef3b6824b580944f40b032c0f56839bd2da8c0d2d2546d7f60d85cba891ee3876ef23b2d56b21dc3395ace930d7743652d994ef26a5523946d1af3c93e9a1f2fdd0a5e2383c6fe9b1862e01bbf012b290ad939f99c9fa4bca7a084faf687d3e412ead58b092f0b2bdd06d8082bc89ac94567ee6d103a3df8b6d6812b818bcb33680436bbac7dccd601b510ad3d011cdb4ad6f19582127bcc97e3a996e98f5f1c7f41c084a5c05ce18e670af4c5f24099c5740b28727c26ef154d7cfbca219c8c9de3d10ce0f8ca6fbb0600603ac57b77c04ffb1e478df3c06ff9e5b0023a664920d56be708ead952f5337c0d4b40ae8c2772117a3455501cf27af83003c11ea60a63961a5ab88ace91302379886e554f68e658ebf2dad4956bc071da7e742e298647a8499885a99e1c0a43c49a66575c2c4ee585a394a45d660a8c3bda0f15dcdee65d53e70654073d90af33d4d7cd1c865d7baf903e9130a3f469793a4f8e560db52c51ad61f041724e8c31cd341b150e99e0ceb3163a035c7b571698dbeab7c78d9b788e4d986c32953f306f5350fa6755c671acc2fb3f2a1e79a9df0a4a960125478f1dfc31537db78286fbc3836eec60ba6b4a18a1b2f7aa9ae3f9fbe069e34942a8247ff1854c900cb6d5c2a44f78fa4c79869f7356a5a56fec69bc1080c5d19d0f50b859f9c15f4505419b88497dfbd7833f4f52053e89c7c183d2d07d2d9b86fb0f927a6809594c5a0b109826b18ae31380696472f17dc63cae1d5867c49728c9b77063c8ef36cec7319c13e3b35f5c60bb38b6ebce8530849ec3adb51131cae847553b321baa5f9c3b83c80322615ba318de310e672995b4e0a7d3b6cf3ff865ac7d814686fea5b1a2bf37a175e79cd25eefa50b7846b033c54354ea634d7b86b03c7a86afa67acadeb8aa0d5d8b57083e046895a4612c6a6d28db8d9b1ee9f399580d8a6ff3d6831591abc0806de4d4df19d63f006dbf4f9fd23e13c9ae1ddcc187b0f66163b45d11f42960873166f07db26e60cebfb3d3a27aff6f58969358eee8935ced89a7a2eeef135fd2765d5387be7d60c748987ffdab3175a171fa0eca53677ed21b0e6bb8f8b49b2f6003c3d2913863a46c10f3d83e07a5733ebf0e171b86d18fdde5d291cf450c28ba6fa745063989ca91d382c912ced1d4c3759fbd3edeca7d1ad4720ddc17ebee5a4206f4e4a87494b761bf68b480c9b7f095f06365806dd5d4516f50ba3677a06d1eb623a929ff120cdb49b1a061aa719482714bc05f6fa89d52d2138ee90a5f0dcec7ca27252c0fb6cee6f181b3327f3db23adf380687a7a481cd9302a0902a24ba16b711327818a32782c6409cc718927d93d02e8b6de416cde839a7e2e6c4eb7e1cf3e217407bbfe15ac0be0a6826d3e0bc10e79c8f80ee462dadbfa20bcc254f3f1c80cca16b560d6917f802e0cc22e9c7e393699eef24885e74c1d0702d68474f01a391f2ad99bffbaa99a9e346e2c0671c32b9d6495ecd6ba18735d2c0b5fb443183d248ebec740eba23114c9f523fbf164d45d09ab0bb7c2cc962ed689fe9ed9f9f0eaf874fa2e33e1bf17cb919950dcb5932ece8a4cc8a0fb67e6400c72f61b433ee85d024f072c26e58362e0f32e3ee69d42be324b34a33f41ee7bd59bd086ef3e2e264bca06709f9a4d54580e1d0323cd310765c07288a027a6660b8ba6a7997eba841b3d6115bc12bf1226f40d834f459e723fb36a03925e41f0836d189c2cdc0e2e0bb7375ee65caab9f804bef1a7e7c2846109f413f44d21081955f750d25a7a05d39ffb47d2ea923dd073ddc196f367c596c338a65da9d9dcfc21d3181b5937502cee8ab15553b3c802cce3f3ce871710ac0c69c35201ddbfcd2b84d99333c802eb40f81deb229afa5213835119b0ba26d263a3542435938195aa82978ce76286a8d7c044b680b371419913c3731b6045d26333b3b5b973f2cc53f5d13c5d7f046c316faf4720dcec660660c9715236ccbc2dda0113166d873e4601e671783e7713d878a6d083f611f06d8779b71cd34ddebb6ede681d9a94cbf68253450bf17fa1449783dfec3c526a0ac77e175d6566042ccdd864b22e6e398942c77a53663b02169ee2791742dca44cba55e6bb37357e4dacd9c8c63b3e8dbe33310d222547fbfff0ddc7c564616fce8523e7d3c3a8513417b09ca2e8fee0252492990fcd4a90cddd026e114b214e2ee365564ea39f187e409ca2e1d8fcd510a55c675c98056a1409ab6140dc4a50f60a860b5829a88b4fd32788384e1cefd0785aeda6240bf69f86ff5832d4a1f86d78b9740f78f609813372b4be758071c5321c23e9f72312e9cef277c01959498db9de8113f0b45f118c1e9f41df612f8f8be69c1ecb26289601738ea65f33b3bfdd9e2aeeb7e2629ef3f67fd00cf34c2165ae71f438a69c583503b070533393689f4f01096f2e60e5bd058ee1a5300ff3f658dfd74d05d25bb97f0cb0ecc3e1d4ac3c5230ab00ec7df3e0a5a6f21bf1ed8f6c0cf12bc01b190f98798941a6750c344963a085d713415b41bfc18216c8219559068f59e25961c271cc4ab08958066118f4da146059ff994a847ec94b03a2e9fe914a5254ceccd5654da4134954cbfff42d0207cf14081ea9a4c7ba07c255537d8a92794ed98b43b08ba0952ef174df01a6c5ee3b7177d93bb83c28ce76a6ea6f3b028f0d88a1efdb47d05fcdcdb1d38f4dea1742778f9caff11bae6a7e5270d832784a51467207300d094b499915792f9988a81d8c51d3ddde052413833c8d659058f236d8f92d86808131f43bd6df7563826ad154c32ad073169075f3f21af79f192b6f7f0c1d197884db85d35f3122bdb1430119ac51db738a82d3b3f3e8a6b4b5d0c9585a18ac0558b61fec98a658c5aef7b339d1f3b276d7cd1fb458bb114f502b0ef3f41b2ecd10643358f416b4b50fa7279899f1473733d371f401ab37032c97588d9f87a2f99a45e42af867d472f01a3e1f4a99a673968d4fdbcd8cb413fc0efa5dfa47c129f696dfe5990a53e71442dfa5fbe1b9a6fc28e53be1d5c06218e49a48f299f678b85f187c27b320189a2bd4449866f8fb9038383e231bc6235898fd5201f82b9a957e0bc1db25812cc344618c686f681ee4134bac70c44cb81bfd23e190eb7cb278ce02f0c9aa80e72b1aa9f127960c2b8147139916373e1566bba9481a03abf34c9bbb8a9ab3497ec43fe79df408820b2e89b0054fa1377f00edf9aee362b6e022ceabd0f4ed89268f7ff3c04ade303d03e96ec7088daf83c232a69044a33bceaf88acecec0b373bf9986fb24afeb0dae109f4d4f45c5096ef057b63e84c501bdbfb013c3b271f622767d1ed32f58e21ad432e308adad8942c529cba1ee69a7bff48c572a00afe3536997ec04ccd2f1b6380954bd133f3ada65f289c4f5b4b7cd2d71b77e0800e78f4f1c5af84b821b170a407338165660b73d6811c45cfc6f145e95e702bbf8bacb35c1e5051ae86d7e3570aa3d8e23c67aeb3bdbe09c0deca7ba6666a02a7145a86270adffc4125f05ad0621832288e1ec1d3bbce3ee63349a076c24c948b76e1f0313397b604140b1323cbc00eb51843d426f87bd44ee6e5438f2939307c6422ddc8b4974fbafb8b1a1f6d68ee645e29a6d57de1120f5bd61c8437cc09920c5d060e614b611652e330ade9aa212d58d2826ad0841b1247ca46254374f27ae89c5601cf34d63ffaaa561f8217c6a5c3dc11f3499a43241c67d7fa59f4d799c1d8a1afd10e4b84cd13d220a9641fb45bc45944b93457a24bebba469509339946f05317df666a579229e41845f7062f212141859623b643b3088fd6a7659355c3e6d38fde98db8cd34c490b91f2ee8480c5245fb14c988180644cc0276a64496f9288516aba11b5902e06d279f4b50c0600dfad7b1f1c2bcce02a0f5a02d303979012640eedd4c8295dc7b9e2cf3f86c6d3d33e45306bd501e866d4dcee81a7dedc0f5d909ac6359e7e2a872d98ebcb14732c7d7573cc5e3271056fc108bed3b85844fc0a35b6be4524916daadfbaf85806b01cc2e9a13979da24bf74d3129f9bd2b2b0852e85ce533260b29b8a7e681308173b799b0f5a52a23365a0f36bdf30a87249a05bfdd846cfdf0c0e8dc527e1ef53d7c27341c5d0d7a700660d8aa51f31ade69786424224d31093aa6f8c54d177c6a7c13c24ae3386a46ef14e78b2f02d701f14035bb05c5a7d7e3239f6ab8b040e0c40f78f4b257948e287693b8df9ab50ab2adf0dcf449542d0e81458641b06276cd83598c678c3cadffcc38d6e524278ff08f868f602921ebb123a6454f013412e1612ef026d7fd652d802bd6041c03a3233479b3ad98280250bb287fa2c04a7c91974af0d33913acc6d9ebf0d37999400fcc790787a86f51d9bbb4918614c40e5ea3df0c28a77c0819987fb9956f9430703e93ce249991f6886c4d1b3635340f1cea7f0a2319a1ce6f5156e87998e917010c34deafb18ad03fed464102ca7e7d18dde8510b5f53378a2a9d82fe4a442ea9fc86524c3430dbb99067883010d69ce1a90010bfb181803fb1850f9359580ce858b493226491835464db7b08df047570b9984f611f48c572add324669394addfae6e149065cb372217e5226ddf58644e5dcacd34dc9df24867530336fe47c7a647a36c9cedb228cc28a3f071a71cc634c51e26ae83e770159ef1001a2535c0c58f5bf1d1cdf9827fab3ce4cc9a43b0316c27f1a03c42d1fb25b2a82a97d42e02a46a6d77f363856a659913ea1f4ca9864b23a7dbd30b7a93417cc2050afd3d80416934466fa1d66cfe942538707c602bf6442224dcdc582ad308d351ebec0c5f2e296248c7157d19d6cd15db3146061f233c63b79a9e0f99648bd40c03a7a0e1e99bd00e64cce220bd9752fb0f15fb76a4648869cced34d1751af191c433f775791d5b3f321dc330dfab1d62972153c9bbd0afe593f49184ff094ccf4995740d290e79d696bdfb3cdabc57c46793cf813e3c06cc3e13b36df877d8ba15bdaca3b732dc5405e767f092b61c8a44c8842e60c6bff3f4339acffe4ebaa4193d82e821e6366605ae6066134e643dee66b53c15f70bc1125f0b47701b49b98058e13328489e35349b1b3827ed62f945eee2c651158f9354b4b4593578b7e3abb30f8187d56f95ba033df5d5c2c2e03628429ac1def8ec9ac163a256c0e1f9649c085d55bde847f338da174481c1c9319169a7b1f32f076d669313028969ef650d3b715cb4860ca1a8d18bb65684ccc0cea9fb69ef8d886c145062837e5e4697dcd0835c2a0c5243771254cadff7d0c6138f1353cce406deba0187a56df2f26832a9a9eae0950ed950a6fad3f089d57eebf13f8d0a445eae49ccd9a3e614b84393e8564a193027ec0583b39d5c912a7c29d75e3d3e0fdcec88184929dda817c57716931718a16a63bc79093322b427317306e82dec1701509e1bcf3c00a7d252d35768ccf522d87ff0b5c0c53993694dd3f023ec048742be938be39266257bf5b25bd502bfad9318a56ba249075ae0944ed9200415333b543e716826dc2727803fd3479153a07777a05bc9eb1019c062960291bcf1e06debf74d76353e82a459f8f980fef8c4b858c65bba03d72e9cbf714b9145e892883e1ec7a1fe177650096fd574ce3fa9d69559f8d5042ee8474f0c77084b223f01882371bc353f1e5d0d6b75063333a15868f54c2c4c1f124d5258eac642f9003acbf8b481f24839555334d68bc1766025e66fd560f8e83e5c87c5af63674e4bb8a4bcb015624cc718ca45f629e9c25000b17728f40101868d5862f81c11577a9e438e6ec25ad25416cf3fcd09d9974cdf5cbd4d71a65c776cf207a8399bc97a666d14df3f24976fc72615af2fabacea96bafdfa675ad3b002fcfcb8729a393e8076c535f91c31eacffac6f48310eacec1df028d9032fc8dff35f04c37c1792e5ecef57e44463d999cdfe4d9d15f039565f0e2d8697e593460cb3c0dcbfbcb7eaac12966b87f91791f8516abad335819eb4f6170b825051ab6a2648d5677e404d8f6981d56e2ab26aed11e8da922f272e5c44b10d17e6d98611cc91d3585b60314b0471184829042d8279191bc1fe6edc876a2d3cad5a8514cf108f1a459f107a1941b8b9279f3260e88186960150ad6d18fdc62e8c9eb40fa7ef3984c31ac728523c2c81448d4d12e64cca10c6ab576b1dc34b05b7d979c4d73591bee5aca09f5b4b89caf2e91ed376ce7ba860c3b43c70c693bdec8d30608c1a52ed23e02c03db3a99a1825d4fd33b98d6a04f6d7c3a512bca048f7985da2133b2b42e5e69c29ce189a0181a47d2ec2260836d38ddc500f53803eeef3138560f20a1b9990c72da4f179179017e1c1c0b1fbb29c1777c2ab8ae390c4fde8beade5c1e32e91d447cfa04931f71235afb5b4e23417f927f11c427ad15dcefe6fd14ef80be4c4b99c74cd21f9136b8c33ccbdc53fd5008d458d07f839bb71733b36cc3e0c2f004e1f058b5b06672867641ec726174d21aedc0256fd57561a0953a2e956ed3675610815447abfc93670a4c42c6cf9c8de03f221136cb89c6f2b57a87d09b0c84aece5f4d7c92d608a3329946377741ddd4a959da48af54b26644223dc4349dff32cd161dfd54f6bdc99c55d616baf75b49e46c7c4cd343f68b0d296ba03d869af09dc4e5ae48bf30f0460e764b6958b7ca5fb1cd3c2e85be8d2ca677f37e302032ae045e98bb00868e5693a2914ab18c97ae8c955ff37325eb578096fa2308f8ac5d63ed226bbff60880af7a064275bf50f8785034fd823504140c52d5ff3efada340363e8618c9a673f3fef130a3fe9737fe1cf3e21986e2346b07fc880f863d6ef29d6d737ecefe7a4eb5deb1e08b5d6527dc57a636bf67d8a9439debafc44a6599d9f914bd266e6c058a468c6500a5ea999cb5d13fb7098c7dedea2496829ff864c6bc236e1d1099924e76edfd3eeddf08fb70e42db99792243e62e511bf2318f04d014ad4b8e38170152e743d332edeb5aff487ade318afe64a30758b7e2977cb0e43dfdda4d493f63cfe1b75ec1f49afee99d3e603929e8b7fdc228867010a988c4ad93ccae7e9603a93b004bd2aad8f86fb0717ca75e477c53d7434fbe7bb8dc7519100533d986a9b2d429613d0d84a286b1ee20d8571c6a3c01b725444ee7c9dc24cc989245163a46c1cf6ca36b3a7ab70c7035e6f7696c6ee5f41a43279b3210c989c8d62d3d66e9345306795ba6f521f047950b6e6bf6c3ab7cd770b96732301aa60f88a69f6320a2a5010bcb4e3113e78bec0a1893bbeede508b2060656d14dca667418c7334bcdb3308be631bf2a26c3e59f9b73c6035a5f988f9774df06be9f15cb51860dd622815ff4f35dd84d42d0900001f074944415403484dcf40fa9543243d38399d14442d07c795efc24b7cd770b967e21a278c778da7ef75d7550db6e806d08501c06f33f3e866669e39decbfbc444e06dc7e0b98085246e722659d723504c772132b7d4ddd2b85a73ebea77abc6223845d6fd3c32f14655daba1b1ef915d77bf09dc2a555887b92306a949a6eb5442e6143b13a3d7474279f4ec880e9182479afee13d369761f837ffb1509c3a766139ffe91b0c12e821eed1746bf914e4869b716d6605a63d3d700fb8410c1364cf86380a2f6846b7ccd2a0fe58de48cf5d7bbe7aebbc989f7b8b40e999aa31d3a3d9b2c610bf6922562960cc5ed78a642deb2bde0de1aee1953564a77697af81490480c00655a20d6d813e43418ab8740dbb2d6a3a791dbd0584dcd6865cd377125e794aad2efecf32a8e3ec277089756257e8b35b6fe8b2cc387d5180b82b3827e303699ac3cfc053cb9e3c49fa928f7c43cac80bf2edc02fff15b00d69862335205f387c593924131f4589f10f81de399baf83e78e065ad57f9197fb2fbc4447518140d9747a9e1cca48cdafc9959d74312977edf2b6ee937cfa90e1cf85fbe43b8b42a895baee9115d46021860fddc1280251fdddb86d1df0646d3b3074e429b039fc113ad690e56bf07dde35608e399a9b8da2102be651a576d47ef5baca40f1460e945ec639802a6d6c0d864fa63c812782f6dcdd511cb779c7b83ef0a2ead56723643fb9c2ddaa1ec6dfbbde8c369213f8954ede6e6e42cb274462e4c477a1868250187e8908f59034f4ecb8657a6676b874cc9102623c7d4d0387ac421829e67732274f5fb33f8d4caeffef14dc9e345a0ea198c51f3e2cb038625d08b4ce3fd3ebc84a8224a85291125d029a3e2d2e385bbbffc07df155c5aad14ef809716edd0f6676fdbef5a0ab0e454944ede4026a4d37dd3b2200c09f8a01546482fda0b6d732a34bde6166a73dcd574173365bf43ae273948d352b42c770bb0f499197a058b3980c040184625915f994679b670ab3073e10ead5d538565b97069158254bae8531a1843f7b185fc53730ba91a5360d4360cbe9b964d77859580d5bdf6673526c882c036f223e915da4111cb8419933284329778e1234705fdc15a8f0e58cedb9381e16e8648dce251f7fd9331155f3a7d43e9658748fa1bd3a46eb0a6714da030623eb9e4994c3e4b5845e253d6416f2ca0cb770097fb4a90fa174ba0bb24d22d4c9b38dbc5a765010be39efa84c0f95149f4c8a40c70f75f049d31cbbf2589fe9ae39c47da96cc759ade51a582dba42c327f680259c500eb6da6ad9ce919085f637934664e5fc2f00dcc16604d401a18ebdb4bc54343397e86feaeef206fec33781d76ed3a76ed9bac5d635af225a428ee130a17ed9929eb14457f6260756d4422bde4a6a4bfbaab840fc7a5920d892b85d96915d0a535bf2cb8706954828b21de2b836e440dc8d2f1580db139208bc2d078faf9d864b20639b3ee97b73d03b1bf6382754685b67f78a9e0392b571bc9ee61bddb7cba9f01c437fdc2e01203b33a397a5d3fbf10ef5b6ef53523fdcf584be4785df5fab82d4f51f77d8225c69856fcc3a0187272f87ce1f0942cb27e7a16593b2387acf348120e8d48249f33c0facd2b8d1e0858440a32d737cc4eca85cb7d27712b61eae40c92d7d9bbe1a2a0163563fce4934338e7a4a01f8e5243f0ac6c1894bf195e6ded7e14d4ba10b4d22b6a5f0f2dd1f49c9da77519932ccc739f4fc206284886531429417e2cdb30fa366bfbfb85d2237d42e927cc443bd937847edd27847edf3b98fe6413447fed1984c9ce70816944bff70dd335f6ffdfd91c5db009a6bff40aa1e77bb3cfb3ef9f61df3fd5378c7ec0fa3b88fd32a0dae71849f7bac6d16d4c7bda314a4d778d56d3773d92e8fb5840639492ee67ffde3d2a99944eca84d8c045c20436bf2f54f062a65c1e04a938085658d4010b8bca79762ded7f910a2068fa47c2cfe3d348614e8530ae359a8646f9bb543abae6cc75375e4caea8ed88e47d010b8589b3f33501d3b2b4499332b4b91e2a610333cf76225db15db8f09143a4f0f1e078f874a4123ef350c3e7d806c7c2670ccc3e19a0108e0e8e130e0e4fa03b1820968f4fd316f8146882fc0b35f3c28a8589e14bea268495d44d0e5ba2f165a0993e2953583128967ecd5e00e7fb85c2150f15dd39235b9bbfea20bc82e47a7c857379b000eb283c352b0f9c5d13e8097ceb77f66979c0928edc894d10dcb00d85530e11b0cf2b154266e6c0e8f2bdd016fd47f7cbfc89d56eb028c60e789401d713d125d02ea8b0f6b57979755da7656a7a4fc9d4d88e5269878c48d00e738915dcec2284d1fd238531436261ec88f9e0393a49d706c68067cf20183b305a1835245e18c540ce634aa6306e5e8130d1af481334378fc44cc9d4660d4f20eb06c5d0ed4c6b3b6c17463fe91f49abb150eb482559ed1207516393058fe9d95a3be47bdf7d1fcd23172e460bd3b03acec8a59bfa85c1b79666ea6caabc561789436a7c1a7d7b562e495bfe8ea6c7a287dc7c91cb7425afaf7d4db55ad33370b12668669e16d94b373a46c157585442f439fa885ab1665caab07a5a165164e1e77770873a97075cb0fa4b46058c6426da6e2ba928c5dd300d6ff13de9ae75d52610cef709812f3c53c942ef421251ba0b3a2cdb034f3d88738e9a19962a53aebaf66c78a9d63e788930226891307db45a9be91a4f96da85d3f77b331311d94b9946fa0303f79f99f67b89696157b19a8f571add382e15a2c72583dbe474787566163c8f9a29821d5fd15c1e6cb39069341587a1177b7b6f9209fdee1ad19dd4a49a79785cafc562acf30ac882253b34dd976e335c0bf07e072c8cb2572cbdd9266c89765870b1302d7011518c510b1b5d128443b661144b73d58a95a39936f5fa1c8057678b7376d32e1c7ef25b481647960ae3976eaf79858314978752e257c2a8f8152409374727ef7b97f3864e79767da415d63845c1d7a392e841b699c3a24a05cf9ccd35ed5bbbd9830082da4ecaba9b6d9357d5755694d67a04166966cdcdd3468c4916568f4c1476392b4865bf50fa6baf607a5d0e73d02f2281f38fb16bd81c23e1fcf0447a80cd813a6595a6f7f25df03c5fad5c1e7a49580e031257812fd6c6635ad6cdbbcd5860adc7f2c0362f0296d63e0cceb9c4d34f3c5348ae572a89f24c1646b8aba0a74702749c9d092f0615c233e8ecc6933a2cd9de62e111d82f6b5ee860af80bf8755c023ecdafff65b0c4ffa66c3b313d95826a640db11b1d061782c74718d839ea392b4ae9e2982273371c3c6a8b5491e2a6d3ebb975d836284230e1104431d9816456fd607acae7e40d83c60a9fa5fbafbc377ce51b0cf4d4996062e26bec9ab784c15172eb299f2178c809f9a45b76325173445ee65c2af5c08410eb0c49a87bd43e0faa0185ae9aea407e7e46993fc8b887f6c99e091bcbab623c61b2168b584898440e5cdfa96cdb8b86575dd028bb4aebe45759e73f234819333b4c95e6942d1b078fade9058e1b86324fda64f085c45b34e3f50b48befed797eb7527d24ad4aaaaa7c8d6954c767e6902cd54a61f6f203f0c40eee4ce7c2e54ebf0a82d6cc5c08754d20e5bd83e167b6a934f72ae9572602bcc57dee0f0401008b23f48fa4ff65c0b563480c593b248e2c1a1c0b7183a3216460344c668031d6254170f34ad33a8f4fd33a78a6687acfced5749f9957673538b6aecb9084baceeeb1759d8628ea3a631bce7e37a7a0ae9bdfe23a6b7795a6e7e8144d5ffc9e1c8e80a106ce31e0c5349d498e0ae2eb1c0de10314a01c104d160c88224bd8cf754e0abad3298abe671f41cfd885d16ffb86d00b481028d50cbc751fddf422d945e0f215f9a9ea30a4849988ffb50f87bd8e5164edf044889b9d2b7826aed03a205861d02a5fa15cb83420c7be847f4fcf81716392e961b6d9ae769cd7bad808f4535f506bc14dcf367c4dbf50b8cc80e3ece058e123d7047a607c8a508e5a8f578a3669f60212353b9f840e8ad1f8bac4d77a0f4fa899e71253eb3d34b6d6c72556e337278f84f82d24e10ca4e67b266b3326b0ef8d4ea2156e8974c7a068e10367053de91845cf60b9adde21f40fa6050952a5eb5b7ea72e7a29388612a26f99bd7fa6dcd0bea17065702cfd644a16593d7fa530aa709bc686b32870e162a45455c1dfe72d840e337260d28068fa0e7beb7fdd4522b5b36e25a065a55ffeca5fd452049b40a8ed150457fa84d05f19a8fcd44f97da52c9feff09039a63ecff477b06d1c3bd82e8a1dec1c2c15e41c221fc37fe0ed35f6cc3e887ec7b1ff709a59f89df0bd5a5d4f4c2749920f89db5cb0ca86ab09045f7c03f8b975ad56bf513966fe50fea4e5f2906cc3a44d06f9886b8d72581ac66da61f08844983c39533b50b51c5eced9014f73b0e2c2c504d9fd25fce368153c35218d2c1e319f7ed861ee9f25d05b3b719dace5e86b3e22058bcfed00a30f28b2c6d3ad5ee271573df23e19204dad1178cb47a563c4a07d42e0d2f0447a0c7d54d1cb880f9efa956de3942f5cb834cb9f850e78ef42786d5a360c1a974677d8314d4b2cd8e0db7ad9371ba376692cb6ecd6671aeac3c404ef6e7ac548b1c8855d38fd65700cfd70b49a6cf04c15668f4b16dc46a9a0cbdc4268135b0ecf60659ffb3597920b975625c85995b00a5ef12d222b1ca3a012fd59f70bfbe6bd68569286d749e686f707ad6314fdc14d49f7ceca2345aad55a7b318546057fe1661f172e2d28c85699b25698c13620fa762e7590c0cbea2104af5b2117beb7a2f4c579b08fa03f0c89a3c766e492a5712b84e9b99bb503f32ae0116431e52b880b97bb28c85699bc5e707356d05dccccf998994abfb08d5bc31ab1f27f708b9136605a5264fd64ada6bb8e71f4679b00f8b657109c7588a46fbb2490954c9b4a608035326f93a63746bd73938f0b977b65261e8267629783233313f39c1550dd3b18aea3a9f8c095c792e3a7f48257452a693f10301e6c500cad1e319fbe3f7781363d7c89303d799d7680aa02fea5e231535cb8b422c03a00ff8a5906affb15095e43e349a66324acee172686027cc7b48c3f648deb5e1467680e63c4ad50893f4b7a110ceaec1d22864a7ccfeeef8c6d183d60174677da87c39b43e248cac844889c97278c095facb14d5b5fdb41644ce0e61f172ead5356ed8567f3b740e78085dab4516abacd298a7ecb36bb563ec66fcd0efa5b60e5f7671844673d4d0ae3adfa84c255a6499d7557d19de3d384f2a062cd4c64574070c2fc45ee38e7c2e57ed2b68ec223f95be109df424d5f4c4c1e1c0b338627901cd778b27c600c3d6c1f41bf629ac96f6cf313f4fdc86929729a4a4b6960f535a76e7ad7ecf6270717b1611a14d39e2edb85d3ef1d22e9192c7b3638866e1a1c43ca06c510d5e0388818120793472709c331d527a0b4b6437839b4cda880c7b95f8a0b97fb58309ea864073c9ab9513b38a64c98393d8b2c1d96408f382ae85718898eb9809d7d6eaf022383564b694eb702417df4928fa5dfe1987a05c30d06543f0d89a79f8e4ca40766e7925cef421215b25898aa5aa3e991beaee665bc2fce39c585cb0326228b26dbd899dbe0b1d895f09fc939d01e0324916a658c5a98e899427cc7a513e5a824523e524937336de644ff487ada368c7e8775f518805cb60a80abdd9bd998d674b15710fdcd2e8c7e8d9ce7ce0af2894b3c7d9b81e74637a576819b92a83d944431562d4c1e9524b88f48847e2355d06d84123acccd84367317c07361cbe02955093c8a0e746ef671e1f290083aa0d1119d58a6e9ae58a61d1c5122cc98954fd226659025484a37209a7eea1041cf30c0fa85b50bd601708981cec5e6349b20fa0b96d4ea1f412b9d15f4c4d038e1fdd12a618d57aab078469636724ebe66b64fa1302e71a5c646b9baf6f54515f02f6ede71e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2e52190764995ee0db59794d5b62f294f77f87f2690d93da3aafad7f3695f3e63a8cf36eaaa7e629ff538a77a959cf81bfebebdea947d83df4baa1afe52dae9ff3c977df29ff8f99755a75f7e495dd9ddd0752cdbaa87e2b8e46b5b64ced3bf7812efb76d52a56b8373cfeecdd43e5f529d6c83e36ca3ac1e715b7faa536ef8fbf6c9675e317bbceaaaae6d55d54e6dd55533598b64cf43d9465555725b4baa5cd036a92a82fd7d36fbfc78bcbf975555cf1b7d11b6cef0191bfb5cdb284f59bf9252ddde98ae5f549d7814fb3634dfb7f5abaa1a86f3f59af2e4b31c1d5a256055a91a6ed5d3daa84e0df87faa0346175940b06a9b7cea35437db2853e45d7e7ed20f8b2eadbffc3dfb3053faba1efb14d108720c516d353f8f936cacabe6d55551e86c76eb9266dc299b8e02d35e72fa69c6a8bf7db465dad68f09aecde4ced1337b00e50aa13eacdb9521c3f7b01993b5edcc4ac2f9fb649d52bd87c1c64ed146b50af5d65edfd76aaaad5085e787f2f269fe968f4452ae0aff88c8d7eae0c88db2a4ff536f605817d33408a36a2ef589caf36c9d56f70746885c216d9f9069baa721d6ed6d70bbffc87d10b9b3de436ead3ce86fac4c58c7dd607c15733be7e5c0486a4ca2d06befb25bef570d149639ec51676aec1b15bb0b10dff290381cdcdd150ee9873b6d174f72b6efc06e6be2ad7e4178fb2721c8e93cde137f5fafb117fcfc071aeb17de1336fa73eddab4d527514fbde0eb689cfb07ebe67ed77d6aeb176b301c012d8e7fe603f2fb239fb95fd3c2b02585275296ad6f8326bec9a5d55557fc7676cfc73ad5c8fe06cac86c840d4938debb4117d9fc5f962f73298a343eb042c68b0a9abf7e21b07b51f63fb6a9f7caa33db8c2e86fa640b6717f6591fb0f00d286933870c7cf79aa88131ed4d37e6ca00b6b0cb0c8eddb2ed27dc78967ce3ea4c5ff17e2f347ccdca65a66bcad5d3709cacd5d4eb8f48c011626c5fb7345e555511dbe4df21189939773febb4aeea216d52cebed41460e9b4ecca6546f5adae3ec034be3ce3e6bbbaa7a87d26555d31a26f515344d39aa303072c0e58ad1cb0d0cf839a9585e7906965d5275f5255f530e41b3219b0749ade21a334ffa4aa29ecb36f3530371cb0386071c0ba1f010bcd40f4d3a1e92839d02d398768429e45b34cf46b35c0a96f0660dd607378141dea4df959d185c0ccd277d9cf3a0e581cb038603d0080d54ef5c5ab92d954dd42f3a841f392ad81e097559f3d51ffa4d80cc002f449a1d6f6a2eaecd38dae71757582640e520e581cb0ee3a60b54faa1e890b1f1dc24d35f6ddaf0cf4a9c5b73ebea51bffbe783296d65679e6c50715b0301c050f4bd0412e017483802339dd8f33607b5b3fa481bd3c36b0dfef961ced86fc4482f4fd1cf67dc7fa6bc01cc0c2678bc06230bc410a9560266e86a4e571c0e28075f7010b1db8685a88a1014d3469331930531818a92bc31bfbfe4bead376f8166f9377ee910715b070c3b3e7ed2f818db6918dfc391efbe30be37627fd179d30564e02f7ff36e12c6f705d990958e7daa9ab430dc5ad619ff8ecdaa92a979ad027072c0e5896052c0ce244b302b59e269baaaad8409fd7f1089d5dd7abb1efbf927aea393106cb8478b4fb0db018d0f8b1b9582ef9784803633ac1febe067d50edd555362faaaadadd0e589f3d21c796b1bee225e0320408a885bd835a9d0500eb225ecb500802ae130c62d5857b70c0e280758f00cbc4fbcb31d4275eb3bdaa6ad0dd9ef3d60658920672c8e05a402d9569a2468d435de920f9c20c01c225f465bdaefaf2df1600ac6b22b824558f69f020815d03353004480e581cb03860dde7808527834f679e794cf25b5dbbe379e90241cfa37f4b0eda6d4a5073d2a5c1547d22c570d577947f877ec3f6a9d52fe89bd9660216c131b275e5ddd058c434315db0f2290e581cb03860dde7808580219ad686e3932e23c0b45156be6e6a2ea5ee9a6284fc1d8e72111052cebea4dfa7998025cf574083be3966ce4bf15767396071c0e28075bf0316e61faaaa471b8a4f124f50932a17d437df8c11f1d022a9d25334c954d54ee8986fecf346001691eeefbf776a6dbadcc5fa39aa984ed508307ddb584e24072c0e581cb08c052c55e54a34d54c696cd3ce63df3d6c0a60614e631b75e5642964e1ce382775d5bb38cefa0e72e37c59a77be1dc62ff981ad39449690c6049612a77808c2eb4a27ab47e402a6a8fec77dd0c8457dc9480ef730e581cb038603513b0709e8c09d9b82d7c0363c5741bb0ce780d0ba968c471680d697ad83782494bcf891180a515d914d8dc349457885a16d214c9fd61a803eb6b92a1f08c3649d51fb09f47386071c0e280d56c9350d422724c6c18b879ae01f0310c58aa5363257604ad81d3c1a236ead356960ce968066069d0b9de36a97a6d037ffb84b50a7dc0c2e78af7dcc0677f11fd5aeaaa7d1cb0386071c0b20c6059b219042c9ddf4b8c9932c4c69022a6d19840e2d882805527f9c51aca73fc11b5267d2aa4764995330c7cf6bf224f98ba6a23072c0e581cb0ee23c0625a86afe417220672f594a239582fefef1e0156ad18ccabae8a6b300838a9ea775c47b2f9aa0b60addadec041c267a2df4e1728cb018b031607acfb48c30a914ede48c3dfad8ebf5b73620460d5e89e676580a17b454a6691bd41075879ec77c71af8dc312965ab980316072c0e581cb05a14b0c4541b9dcfea46031ae170cc1d148361d5556f4b9ad71deb5a0a964de380c5018b031607ac16052c4cc391c67cad81f5351e93d5a560d88386d6a0a481a9396071c07a28008b2df685a602471b75d5fc76aaaafe4602d6cfd2a634ba497ce5571a009f070bb094957da5b93b7fc7fad2a5e1a448c1b05f341c085b9dc101eb01042c89a131cd145a150e5896012c4c69416e2a539a8ee35cdcc482d1610deaca402931981818874af409b50ea7bb085868f249053c7ebcf3255bb5154f06a5a8ffaa06febe0f0f1238603d881a5652f5c778e42d3b313960dd4d0debeea4e6341dd650bd1001423fbee99e6b5829675f9202651be2ddc2ca3e47a470861f1af87b0ef2bb73c07a20018ba9d4eaeacda624bd36055818f42766d6d7e3f4e68075af00abca0b83431b89c32a47ae29534abdc92217c6c5108297922a0762aa8ec8fe6940633716b0d03fa52b42db8006a52b31765a4a78be70a753be3a0a1df31cb01e4cc012b3ea4dc9236b0ab00c25a972c0ba57c9cf4da4e620f11d161535836d15bfc7fa2e14d91e92aa56b17b4f6a66d59c9adbc68ef5220d10334ab4363577e61c9e1a2b46ee73c0baaf010bd3392e35f0e050a53e8e47c01cb01e54c06a41b6069d59b65b3acd43f3ed04eb6f13160f11d781b2f275fde211a60216faab2413b021aefe6b0d698d22d5b5543d9c03d6fd0b589f1bb0f791d4ed730e580f2e60bda43ad9467262d71a183756d0790b9f8f19f3fe16469537043c789a8973a15f4ddb54c09258524f98f20c903450ef1972c0ba4f01ebb0010726528edc6ca73ae586a5be8deb4b2cbfb5e1c107acca49fa55638c6958b801b58ad60458081278a82271435d36002e97d97787ca636f4ab0a005faaba4fe6e18022c8c4ad7f78d99ae6155864b610cc6966afb445fa3e38075ff0296216648397a780ac6be18e7b710ebbf1d7ad0014bf6fd98d29066582eded0fa8a50888c9c068358f57d3f4d895ccda811f01001ebd58caf1f6f8e0f8bcd9f8fc883651c23e93738076231110e58f7b788be061dd586a107fe03fa1df074c6d0f136aada52e1810fcda1b2e591eef716b0301eab096de52ac6e5e133409ef486fac0d3649d7929d2bf5c6981325fb76b58483ea8232d34c61cbf83889003d6fdaa61a9abe24467a8ce49491be10f72449a5bb1869d5e192cac188c9552a40df8452345342f6319740e58adb2ccd704a6ad14484c9cc4001bc271918f8aad039daff2f635a04798b7a7a1b419791d886b4d5ded5f3f54c254c0c231a0f66f9486a5cb2b8cd00fd1e180759f0a52d7b2451226bd158546aafe5e111f3cd3b6f44d1d244e33f0e0f51bf6fd96a145c001ebde97aac767231dc05c6be2597e8b41c5b7af81caf58d9609fb731c573016cacc52f53577cca3bacac64893f00ee6540e58f7a9e84a32554f909cef571bd190f0ed5b294612df5e0afe4423e5e0e5769e2d9a4454e33960b54ec0c2cddf2ea9324b7a969a469ee5ef626cd56d6b00b3221aaff82c950cc39343af86d27dcc012c89b3fd7c83ac0cb773e4e763b0a97e860507acfb58b04880b4817e6809c6008c3e46ff86fe290d07acd60558ba6adaa86d57bd23818ba5d7c1398cf93274da680e60b549ae7e43bae79f1abff69d49dc1cb0ee6bb3b0ea79f44d48817edf5a78a122eb630a6a7286981f3860dd7bc0c203151d4f14faa1c4cd7cae118ddba426168e50556523201a0a423507b0f0d4554cfcd669fe865c1967d9fdf871c07a8004178bc421b4049dab16d6ae94ccd4706f74b371c0bae780a5fff26aa3aeea27f9b37eb1d03a4831544ebe593eacd4ea17a4793cde480805defb340e580f92a03fa102fe8a6f2c71b1aaaa72d942784ff20f103308e33ed12dbcca4918cd5c3fe6860356eb052cd4b4d03c6ca7aaeac240668e14eb845af745d322fbab77a056852f2bd4ac4447bb85010bd715f6cf5e8a3b0d1ef6e05a56550fe180f5800ae65a89d57ad5d59ba524527300eb7dd10cc4dc2d2312673960b51ec0ba7d9c582aab4a853e48d67e356d1d54af60a660b068061ac1f6600e60615c95f81d5d5e6143dfb92cceb3aada8903d6c30260ead35618a5adcbbeaf8c6e3892bb3a04178e31da1497fb53d0f724be8090a2585d35bbfe1ac05419f9a5d29436c5854bcb0196b2f275cc0d43d51b6bbd61f060fd86a69f983fa63cf3a23965cdb9b47e1123d995a73b4815673ceaaf010ce21439af5427db989230cf850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b172e5cb870e1c2850b97075ffe3f9c7ed0c6d0a02d500000000049454e44ae426082, 1);
INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(3, 'Samsung Galaxy S8', 2, 8, 2018, 16000000, 12, 'đài loan', 'Xanh', '64GB', '2K+ (1440 x 2960 Pixels)', NULL, 1),
(4, 'Oppo neo 9', 1, 7, 2017, 5000000, 1, 'mỹ', '', '', '', NULL, 0);
INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(5, 'Galaxy Note 9', 4, 2018, 2018, 22060000, 12, 'Hàn Quốc', 'Midnight Black', '128GB/6GB', '6.4 inch 2960 x 1440 pixels', 0x89504e470d0a1a0a0000000d49484452000000960000009608060000003c0171e2000070b44944415478daecbd05749557baff5f88bbbbbbbbbbbb1021018284282109810811e22e4420218440428810c35d5a0a454aa154a6940a53819652993bb3eeffdeb973e7da7c7f7bbfe79ce424444eb0b2fe6bb2d6b38ebf2739e793eff3ddcf7ef67edfc23f7ffef9f30a7edefae747f0cf9f7f82f5cf9fffff81f57ffff77ff8fbdfff8efff88fffc05ffffad7a9f8f77ffff719b767c7628f7307f7b1e9f5f982d7f7a0f72f14fff66fff3615dcf7d3f7f8dbdffeb668fce77ffee733413f235e62aed7d258ec3d17fa5c389f0de73a7d3efddede48b07efcf14774b477202f2f0ff9f9f9282a2ac68e1da5a8aead434b5b3bca2bca51595181aaaa6a5457d7a096dccf89baba7a266a49d4d7b3a2a1a1098d8d4d686a6c465353339a9b5b986869d9c9c4ce9dad686d6de38af6a9ebf4b19616f2fc267a8c66e658f5f50de4bdea5153534b7e871a545656a1a2a212e5e515282d2d2751c6fcbe757575e4be72646565213b3b1b5bb76e65fea6ed8545a86f6ec58ed20a1417d3bf6d07794de9d4655959d933418f535e5ec95c5690bf7d3aaac8fb57325155553523aaabab67dca6cfe1bc8e1e871e97be270dfa7b141515a1b0b010dbb76f67a2a0a080b9a4df01bd4e83fefeb9b9b9d8b66d1bf3f7d43534a3a878073233b390919181b4b434a4a7a793cfae95f91edf18b0befaea2b787a7ac1c1d1056e6edef0f50b4650500482834984ac40485834024322c9ed48d67dc191531112c20a7a3d88b95cc18ee9e70705738e35fbbee9e3d0c766df1734f57e11335e37753caefb69c4c6ae465858380c0c0ca0a6a6c684a6a626b4b4b4a0a5ad056d1d5d72a9cbbaada5c384b6b63613acfbe60a9d19b759cfd559e0f90b1de7795e37f3bdb5b575a043fe0e6d2dfa7be8327f9fbaba3af3b7d2e7787878e0e1c387bf3f585442cb2b2b10bf663df9e00da0a2aa092b3b7b985858c2c0c414fac6264ce81991eb862630303486be81d154e8e91b32a1a36f005d3d03e8e8ea33a1ada3c704f345b243937cb09cd0d0d466425d436b46a8a96b32a1aaa6c1848aaafa8c5056519b0a2565d5a9505452414ccc4af2df9b01434323080a0a829f9f1fcb972fe729f8962df4f8329e8ff37b041f1f1f04040420232343942b93a86205fef18f7ffcbe6051afb12db7009bb3b6414a5a9e09475737189b5b30607182c245a1e2068b03150d0a95b61e0b280e5c0b41351f481c98b801e24044e1e18482a23213f20a4a4cc8ca292035351d7d070e4157479ff9c0972d5b364f2c8700bf08b9e463ae8b084b404c42967c394253cf11101066823eceb79c7fdee3898a8a425a5a9a842c135252324c706e4b4a4acefb5a0a84a2821ac4c5a59e798cfe2ec2c2a20bfc0d3383be077dafb2b22a94ec28637cd7ef0ad66fbffd09c9a9244f6fce26bf9834014b164eaeee303233e7522b0213856a1ea0384aa5cd56aaf9805a4899b8d569b622cd0514375872f28a909195474949298647c69974b11058fc022250d6b02197420c38d2d26ae45846e418aaec2f891f12e20ae40b5720d7052029ae480092649e3bfb58b2b2b2505555e50a9a963498a0d79594941880e6fa3dc4c424c83f991954d4f49e798eb8b8380326555d5ec1929090201eb21c8d4d2df8cb5ffef2fb82f5d34f4fb1212915a9042c090929062c076757189a9a3140718242a5c7051537504c1095d0d4d57b06a6d9698f031437580ba5b9d93071148a03140daa56f4b18aca6a062c2de23f16026bf95b347d084f3d474c5c0ef24a7a101193663f870f2222d24431a41898040545c9172cc0b37af01afcfc82e4f756858282ea9c6ac6ada0bc804561a483939d3b3bf0a73ffde9f705ebc9939f08586948cdc89a02cbced985016baeb4c7014a97a63abd693fb558ca9b0dd56cffc40b54dc0ac50d150dfadcaaea5a3658bae48b11225f9c30f8994b21e6928f4f808420fb927d7d39fb3a93eef8994b3e3ed6f5e9e09bbe5cc67d9b9febf6dcf7b1546eda0fcda57a2f23a6c1aa24a3c35d2413fdf6fb82f5e38f4fb061631a930a3960d93a39b37cd502698fdba42f45a516f352f3a5bdb9548a13340dd2e754d7d461e4f0044c4deca1afed02031d57e8693b425bc30e5a6a36d054b38486aa15d4942da0aa640a65052328c91b40515e070a72da9097d5829c8c0664a5352023a50e6949354849a8925022e99015126224c465991017938598a81444452488c24940588886388404c59810141025c1b9a421c2783b010a3a3f0770be1946fc45c12a2d6381f5ebafbffede60fd88f504ac14aa586c8f65ebe80403e2ad7479008a5773be904acd1edd2d05aad9600d8f4cc0d12e1886baeed0d37286aea603b4d56c0954d60c54ea34942cd96019b2c1d22560e910b0b409589a4c50b828582cb89421c580a5cc064b8e0d961c818a8225c90c00b8a19a066b3a58508930298ea5a0fccf80f5bc70712b565bdb1ba1583f12c54a6752a1385bb16c1c1c19d3ae3b0754b3cb080b951078f152b3477c0ba5beb980e2c4b4624d326019e8b83050e968d8336069a95a43538540a562093502950a31ebca0a042a39fd1960b1548b82c50a6949aa5caa909124604928f10cd66ca866c02520c495925f1e58d4bc979655a3adb5f3f757ac2714aca474a258990c589264a86c4dc0a2867d31a55a8a9f7a9e51df42507183252d43ccb782326a6a1b3132320907db00e86b12b55277848efa34541aca54adcc0858c68c5a2913b55252d065a7421d365cec9066858c14498b0c5cca4c4a94105366432543d2a0f40ca816038b059520512b61b6af63f9be692ff7e260b1148b82f5a73741b1482adc340d9695bd0303d66ca038b158a1733ea8e652aaa58cfc66434581e2849cbc1251ac062615da336039425783a6419b69b52250d1505564792b0a96a29c2e130ab27a2cf5923362858c01146474204bc09295a060a910c5922750c9cf0bd6426950905d1763d220f557cf40c5ffc21e8ba558956cc5fad39b61de2958d4bc539f45c1d235345ad45371a05ac8a42f36ea9b2ffd71a05a4ca968d0f42dafa03865dea962e99134a8cba44162da49fad320865d4dc984782b1396b752d087921c512c397a49d44bc184091579e2bde48ca128abcf024b521332c4c04b8b539fa5c065dce5a6c0e245ad18c52250b1d22047ad845e8a5acd50acb20ab4b775be09e69d82958a94f4cd8c62b1c0b29f01162f9e6a2923bfe729272c04150b2c2506acc344b11cacfd0958f653699042a5ae640e354513c65b3150c9138f2547409327a95181fa2ea2682455aa295a10b84ca6c0929322609134282da140c0e2562b59c65f2d9a060545989832edfcd3e58e97a5563353610551acdd6fc6a8909af79474920ac52599ea3b07acb98cba86363b9ec3a8bf8852cd068a1b2a1af435b48e453d96bd952ff4b80d3b2705124552553026614a82de47a1738096b20bb4541ca1a96ccb1a3192c7a99229c8905122e3b1586a2529a648429ead56b2042ade4c3b2705be1eb02adf0cf3fe23dbbc27a7b1c0a28a656947fedb0d0ce755aae72929f032fae365043817541cb098544814cbced207baaa366cc36e3905162b2ca04180d254266952d595187c4fe86b784157cd9d80680f2d02963a512c25eabb0858b29233c1921095638f066518b07833ed5c6970b9d02c7fb5fc85a19a4bb1de9872034d858cc722616167076d7d83450b9f7341452fe9eba6cdbe3e135ada7a53a1a94582ebb68626f77167c6ec29a0e9f7d222b06a30b0d101871c818f0396bd85177454aca1ad6cc5a4410d657306304d656b68a93940874064a0e10723ad20186b07c050cb97c0e546ee277f370551c1082ab2ba5092d1624685d2b4d4c0a895c22cd32eb9b069a76990abc43065da5f2158a565556f462a7cc21e1526538f45158b80656e673b05d6528a9ff43e57370fd83b38c1d6ce81091b02a92d49adb66440c00451431b12ac4b3be63e3b074738383ac3c9d915ce2eac70a2e1ec02472756d063d2b0b3779c11f4751c859b4a85042c5d55f237109034a97291eb3a6a4ed023ea64a0150063dd5098e947c2dc20925c06c344d707469aaed067c02230927449c15294a660a9428a018b002c26c780c5f1568ba641419129a59a3f0dbe7cb068e5fd975f7ef97dc1fa810316271552b06c5960f1aa543468da333523269936a29134aa4b53a9810174e82080842e57e8701e2397c69696d0373625cf27b7f50d99f9471a74429b3b34747459c15c27418057d322bf13093a3b40958b05d6041c2d7d61a8ee4c5489c0a2e109434d3f18eb04c2543f021686b1b0365e056b131a31b0340c85b99e2f4cb45cc8f3eda04b606481a507795a20955262a74132221495659bf6a51744a7a11262cf45f2bd146ff52c581568ddb9fbcd18152632606d9e0116f3e52ea1004a3d94a1910934c9976f626d0ddfa02098d09e2e721f6db9e140c5145ec9a589b515e2d7ad87a9b915cc4cad604ce0d2a7cd82143a72c928e61c80b93911a5f20a86a38d190398b2ba060cc9f165b952a18b75184c75fc614a95498fc0a41f0b2ba355b035590b7bf34438586c20b1160ee6b1b033098395812fcc755d61a2e1083d924235e8e8912896bcb43a512c165812a21cb06478038b4983c233a770d86970e6e4f6cb05eb0df35804ac541658b4e460666d3303acc5da5e6850634e95832a514a46064c8d09547a04262d03a6a586bb0547c7c8082b56c691d4e6024f0f3fb8387bc2ddcd0776368eccc4373d06a398042407677bc4c684c13fc09bfc4eba58171b8db03549880af1243e4d07ca1a1acc6ba862d202294d856e0eab604595c9249e81c9ce8cc064990447ab14385ba7c2d52689c43ab858c5c1c93c12f62601042e0f986b3b93d124499d8aa6d360b1d3a0041d098a3d4fa59dcbb4b3a7715ed64870be02294bb1de00b0129951e1dc602d960239a3400e587ac6268cefb134b1858599232cf40848c4acd36e8929b84c4c604dfc97a73b81c9d615063aa604000b062e135373720c63165844fd2e9ce8c75fbe3b81efef5f8425499b666636c49f39c28480aba9a303150d4d062c671737920aeb31323c096fe764d89b1255324f266931054ed6e970b2dd0417fb4df0b0cf80a74332bc1d12e165b71aee36d170b60c869db13f2c75dd480a256a4dcb1132645428a50669097689419405164fde6a8e34c89ac6117ca92586f9ca0d6f482a648195949a01310a16093392ca185fc3c3740d775981512c028401b9b4b170808b8d17ca5373a0ada9077b62c075d960d15448d3a6af57006cac5dc997ea88bd550df070f185b5b52d8c2d2cc9710c49aad3c1b50b87f0d79f4ee0cf3f7d001b1b6bb8395b6375b4379c9d6c9854a8ca06cbd59582450ba447e0eb920e478b643859a51185da0c57db2cb8db67c2c331934097053fd74d08704d819ff33af83aad82876d3451ae30d81878c1944e032999424d46070a922a240db247837424283a774194a74e8697382fb858dbcc1b950a9338a99084a9d5b3602dd6f939950a75f5994b7d1d4318e91ac3ccc0149e9edef00f09859e21573a24003a39bac3c3dd0f76d64e880c0e27b0b8232a7625a3681c139fb03a0a2db5db91bf6d1374740d88aad1d7f89214ea4a7e475d062cfa7e14acca2a62de0f1f819f0b4979164970b64a257067c2cd2e9b409543a0ca819fdb5604ba6721d42b834432823d3612d012e04994cbd1d80f165aced05732873a4d8512d360898910b522a3410e540b56dab90aa274d29942c52935bc4ab0a615eb0d699b4964c0ca607ab039603123b0054a0b738145958302491589d3796a6d6787c8b8d8a9def9a991217d0e095b1ba26c0428eaaf42c322101c193965dcb9831e579b8493bd39c2fc1c606765ccd4c054c8ef43dba65d5cdc0958758cc7f2735e0b27d3357036df48e04a6714cbc3611b7c9cf3e1ef968720af6d88f4db8228ff0cacf04b4738012cc83901ee1644b5f4dc6144e716e5085892ca90a4d33822b22cb084973e2fc8cff8aad9de8aefa54235a31f6b47c59b0c961503d6525a60a64685e4cba76ac494188849d727f74d951a0ca6cb0d3aecd11fbd6d4014ca8cbca7a5ad1db40ce6180d92df45932935e8428b591bc89a565267d7d86821d6790aac09f8db87c1c52008cec63170315b07578b14b8db66c28bc0e5e7528020cf7c44f8152036681be282b6202e2013919ea9f07788879309194dd2c96b390362ded520c5f82b59a256d4b48bf3d0c530bbc420f84a4dfb338ab5838e0adf20b036a66c62c0129b05d6523a166831d5c7d79f295c5a11afc41dd6ecb0b1b18395950d139696d6b0a497f4367dccd61ef6b4e8e9e0c4841d31f89cb0258fd1c7adc9eb695891a0055837774f664a88a6c26a3658110ec1f0d07581ab8e3b5cf5fde16a1405770298bb751a31ee241dbae623d4bb003181055815968f84b06d880fcc428467223cad2260a5e70a3d45132852f32eca068b67b512996e8f611b7656a7a8c02b4981dcf1c6a5c28dc4bc2f06d642cbb4b8e701e96bcccc2da70060820b2c1a566c90acd85071c2828246c2821de616563027c7b2a097eceb66eca0c5589a6a69fd8a8e64a7c0221e2b252a036bdc5722d62a088186eef0d27182879e373c8d56c0cb7c03190d6e86bfeb5684fa6c476c48111222b6634d781e812b13c12eabe164e60763354b28cb68b0144b8477b004b8faaea64b0cfcaf54ad9ef1586f8262712aef142c5136582664584f2be84b81eac5da8be9c4b22c13b32797e93c209d18e70e0ad2ec7075752760d5e3f0e163484baa41e2ea5224c71620293c0deb3da309647e08367085afbe27bc4c08603649f077de8a489f22c48716617d6411d645e421d63f157e0e2b60a5eb0875793dc8882b3145d145e70517ac5df1bf72b5e22816674ae78d002b71633a9253581e8bc67c602dd4b3bed48510f43ed6ebe97135984b6515f5a9e3703a182858dc70cd0515fd67a0605555d663f4f0096c4adf89e4c44624afab43d2da2aa4ac2d434a5c2ed24292b0d629142b8cdc8992f9c0d7221e014e240512b8d684edc086e862ac0bdf8615de1be06ae60f7d6513c849aacc09d6bc50cd59bbe27b6d60b1da6676bf291e2b1d29a9d360195b584c8135d71e0abc2ed99a4fade8759aea683ab3b4b28383239d707663c2d1c98da9c8d354c9e95ce0052c5a20a5e67d74ec04b664772325ad0369a9ede4ef6a455a720b520968a91baa91be760732a248ea770d4794b127024dc311e040468704aeb591a5d8184d200bdd8240a7185868db4345460b1262b28b8325c8f656538b2504e69c707e5550cd9e847e734685c92c8f25ca068b4ef0be8c16e38516433c7bff74cf15e73d164b8314aa99609d445ee1016cc9eb41f6d61e6cdeb207e919bb91b66917d237b56313012d3da9019b36542273452ad6dbf921c22400c176c988f22b4662543912a30bb1322095a8962f74140d202daec080c5f36890b358e277008bce15be19e6fdc913b6c74a67a0a261643e13ace75db6b5105834cd2db440821e93be272f50b1c07267a674c68862e56f3f80addbf62327af175bf359b1655b2f32b7ec2590ed4146461732d2db9091da882deb0a91e11d8b58537f84d8a7222eb81429b1a558bf220f21ae2b61a66503792965668e70d166be45d4ea5582359d0a09586f422afce1c7f9c17a5eb59a0dd55c2b97678335bb43743658f3a5401af47776766629d6d8e831d4e4b6a2a4a01b85457dc82dd88f6d797dd89a77805cef2797bdc8224a9691b90799595dc8242a9695528bace0d58837f543885326d64556104fb603ab8233e062e60d35796d48509f2528310758e4b6204d815c2586e542af55ad669af737acf2be3179131758e650d5d4e219aaa52e87a7f7718335573f3b3d3e27152e0415072c172615d6607cec38ca129b51145e88f2a86da84d2a4355de6e9494f423bff020b61510c8f269aa240a96b317d9d9246566eec6d6b4066c0d4d409c6900c2dcf391b4b202c931db11e11e0f23754b323a5480e89c60714c3bbbc4c037bd4fc4ab9a705e10acd237aaf29e8e440a96a838e3b33860f192029f67393c7d9c763158ccae5991e0d4a8e875fafcc552e09462b1c19a248ad55bba073d3b7ad04e5260596207b68717a332220bf59b2a50b1631f8a8b87880f1b404e7e3fb6e4f611c048aaccde83ad9b9bb025200651e6118809ae44faea0aac0dcf8293891794a4d5212a2cb5c884b320ab916f8ed6e3d7a7586f18588c7917652996a1d934584b816aa1e55bb355893e97ce45725a7358bd5f7aec7db4b499d7f2920239c131ef93c393e849484177522ef66e6b44df8e2ef455f4a2795b1f0a621b511a9081e6e412549612c04a0960db07919d4b0023706dcbd983dc8c5a64bb0421cc3a19497175485f558270b778682be933a3432141f1a95ef6191b7dcc6a3d9e068befb54035d363bd29957706acf429b00c4ccd983ea7c5b6667c919536b30ba14ccd4a5a9a1552f3fbaab9c012214acbe9c71a1f3e825a9f95e8b273c63e0f4fec8b5e87bd59a5e82bdd8543d5fbd05e4852624c132afc93d1925581ca8a43d85e32846c62f6b71105dbba750fb66f2c44b2850f22bd4a90bdbe1e491139b0d577848ca402b3a3ccb32b7038fe6a2ed3cec76c35f9bac02a2dad7a33cc3bab353995e5b1d86019b1c17ad92970a1e55b9c7a152f35abd950d1a00b319849e8d1534858770c213e7b91ea9089166b77f4d9db617f441c7ab3cad15fd98d43b5fd68cc1bc036bf42b4c6a5a396285a69d920f15e03c48791cbad1d2858b11e31a631488adf892d6b2b11e11c05451935880a492ed2d3ce8a654bf556749fd365acfd4e99fd50d9b16c392b78390e05abe44df158d7ae5dc3c6a454c663897052210f602d45ad165b6cba940afbec14c8018bce5156d7d4636cfc14ea9aafa2b8f25dac4bbd80c080416c70c8419ba533fa1c6dd1bf6a030e14b662b8ee00f6570d61eb8a263404ad43134999a5e5c304ac311490d1e4f62d2dd8e2e88330d7edc84dd989d4c8ad30503386b8a8d4f4b22e81f957382fe3e35b5a1a5cfe16c40848ba44fdbcc4a4b1424a1eb1522a0895908795881824e8f116d8b48d53c72a61971bce9f3fcf6c5cfc5ffff55f78f2e409ae5cb9827ff9977f797d601d3b769c28560a3630608933069eb6112bcf028b5ba91652ab85b61ae215aac50cfb5c60d1f44953e161a2580579c751b8e322ca1baea1a4f62ae2d69f83af07f150562b71c0dc0803fe013890538591da3e0cd50d217775176afc56a3a9b493c035829a8609e4e6eec58e75d9586514868dabdb90bfb116ded6fe90225f34c7c073cf0bb2468282cf8c06e70581bdc92e552775f29a5809393419da62d8310c076dc2d1631a846eb330f41a45e2a05618bad59c90a2a002752161d62ece7382258512da8fd5b61bed1dbb30387888d99e5b4f4f0f090909f8f2cb2f5f1f58478f12b0925298f664512eb068719417b5e23505cac8ca429ac62b502b6eb026c64fa3befa1c3293879195790485a59751dd7a03d98557e1157404abedb6a1dbd418036e8e3890594c94ab0fc38d83d8b6aa0b8d04ae868abd04ae211495f4a338bf0bdbdd82481acc4771e62e24856540455a032242e23cf45d2d9cbef8889a2912154a949147bb7d18f6841660223a17551e9b10631309174377986bd9c352c7117ea67ed86c178d1ed7288c393a234bdb14f22252ac74f9d6322628a092e252acee0602d6c0a141d4d5d5c29c8cf0e906bb252525af37151e3b7682016be32cb054d43596bc31da8229900dd6f340c5016b3ea818b024a5997585470f4f609c8c08076b0ea1a6ea3c32369274977716158d5751527713011167106a5f85dd262638e46887fecc1d1869ecc750e308b2235bd11ab206f555b4904a0bab3da848d98e04e320642412539f5a076b6d1b880a4bced89361bac4c0cf53a59d8fdcef282c8a1a4327a4b9ed40414c379a82f360afe74a060762d0555380a1963a8cb535a1afa1063d0d1528c9b3faedd5958c1160168258e734682bb84242480142cb0520482095949064ca0d6ded9d983c7a0cefbcf30e52535319c55ab76e1dfee77ffee7f5839594b4e959b078dcc2f17937f278596ac50dd691b1a318cbda8113213138969a8b7d9def203bf930b2334f3069b1a2e936fc23cf20c4b6127b8cf4887239a03faf1a63cd87d0df7018193ea5d815bf1135b583c8cae94549f15e147b0421caab1cd5b9dd58e911cf54e1850445599ba8f10b7035f32d5e10a52ae32e2c8c6a4b7fc4fa55217bf50154046e832cf1533a2a0af0b7764095a93fceb947e07dff105cf5f2c571274fe2119db0d1dc8680ad0e45593232e51782b404f9c797d68192843264087432e43360ca0d6d9d384232d1b7df7e8bcccc4cf8f8f8203a3a1a376edc78bd1e6b6372ea0cc5a2fde97421e8cbdc71ef19a8646869416ad13eabb9bcd56ca858a95086016b62e2248ed5f6e0484e194e46aec099980d18eabe8cbccd13c8dc740c3beade4359fd2d78069e45b4d536ec3754c740801ffa4ada70a475041d65e3c871da845d9bf25059334ad2e2415410058c378e4151f6016cdf50066519750809893c3385c3da315960c1130e2813102b0cace163980a7bbb32ec5ad94054480f3aaa0a08b3f6c20d8f08dc8cf5c0fb31baf8329644a0363e70d3c41d0f3d7ce4618eab4e16e8b2b522decf0046e43572d21210226ac54f3d963867c1ea2e062c6ae0e9797a7c7d7d19af45cfb7434f10f55acd7b12018be96e9805d68bec0eb3b85249bd14a8b8c11a3f7a12c387afe344432f4ee6ecc0f988105c8a8cc7c8fe2bc8cf18c5a68c93a86cbe816da5d761e7710ce9e62b71d0480d07e356e3504d2f26db46509a358472fb68b416b5a2ba6114e5c5ddc8b3f74542541bea0bf6c2c5c8750aac6737f958380dfa8b8b61b3453c7c8dcb11e9bb175bfd330914fcf0b533c3885d14de8eb6c2fd047dfc7bbe3f9eaeb5c6a3ad417850ec8f4b695e184ef44703795ebbae122eda69e2a29329baaccd90616484007555d8a9a9a0aa9465de8f1c3946e20873e226070707d8d8d860c3860df8e28b2f5e672aa48a95360d9691f1d422d4a5b7c4109562b7bf58dbdac3c1c985792edd339ef6ac53c898b6627ab60b7d43e63af35c1b3b189b9831cfa59bbdd1e7d3e7d2291eeaf3e86d0a249deaa12dc97383558fc923a73134760b43433770aab11be737e7e0728817ae44afc1d0c11bc8491c42c696b368d87d1b09695760e374103546b6e8b7d4c1814df938dc3c80c956921289ef69f58d447d4d1ff12de439ab521063bd09f53b86901c9ece782181a991e0ecd1e0dc752a7e62b2e3e49488faad458096153686f422d92b89186f3e04b978e163cf60f43babe16fc591f861930fbe0c77c08481264e5968e283085dfc797726fe732c1b1f3646a236c60771ba5a4850245ecd580e7db6da3849146dbcae063bdb3b18b03ef8e0037474743006ded0d090f159c78e1d7b3d601d3f7192a963cd48856cb09e4fad5860d1fb83c3c29995370aca2acc220b1af478de3e7e707673678a9af43aad4179787a33f38774344a1763d04512f418f4713a974877b1a1ab71288814b6d9ea45a782e812fb71322aece9bf8dfec337303c7815e7cb1b7179e3065cf375c6e5f47cecdb77039b570f626bc145d4777e00cfd0b3f0b5aec45e7d350cb83ba2afb81547db4671a07e0269aedbb06b432a2a6b8751b5bd05696601d8ba690035d9ad509452e62a31f0301aa46091cb285925ac358b83260133ccb518a52b6b989318b8185be3aa5b243a6db4f06b963fbe4ed646b122f1bbc44b09125f2644e0531116c06a7b7d4c6c0bc0d3837978da578c73db56a23821145ee626d092954629536e68c7d933e7f0affffaafa8af6f44484818525252b06ad52aecdfbffff580f5ddf7df2331712658bac6c65054557bee063eaa40f4d2dcca060181c1ccea9dc0a01066550ddd85d9d72f80d99a882ec5f721d7e9f1a8127120e3acbea1c7a74051d05c5cdd19b028549e5e3e4c7ae4068b42ca941b264f6277c719ec3978178387ae60bcff3cae6ecdc595f860dcf474c4d9d62134949f43f2fa23286db881eca2f760e9721c9b8d23d167a880832bd760b0761f8eb6137f957318e5243ded2c6a4265c3182abc43b13aa401ed657d7031716503c5fb6890deef222e8e64a348689091a5869c399a1247a1ab6e0a6b3d6dd4dac462d45e0e67fdccf17d92342a15e420cff7ec9410ad61294b886283a7038e9467e09bfe1df8ffded98fc7c77b7074df5eec6ced98dac668e7ce36e69c8b99595b101e1e8eb6b6b6d7d7e8b714b078f556f4ba878f2fbcbc7d19b0e8174fa1a26b0f295446c6a64ceaa370d1944b01a2f7d3d7d1793f7a9da637ba7a87be8eaa167d3fba4716bd8f1b2a611131a61f8b018b98f7fdbbc6d1d87c117d873fc250df699c6f3f88eb496bf15ea82d6e11151d1db985dc0d83d894751ecd7b3e4460f405d8d9ed411b1996f75be8a12fab14633b0731de3a8e949056b40747a0ae7118f51b33b1da32098de523488d489f32eebcb6c7d0c7d404f891aae7014b592d924af911e4b40585b1b510151645948b3f31efaec8d197c26fa9c618309187bb98e09cc75cce3e73193fb95492944098a531dad2d662a2f7c08cbd1b0e1c38c09c44337ed51aa25c21ccf5977936d605c1a293d01cb044c897a4676834556d5fa8663557f7c2ec51208563a9a505eeb2c242869d03150b2c5766f9d7e4f849f4350ea1bbfd289a77937438f40ec60e9cc18d827cdc581580db5ed678b7b8059dad5791b4fa308aaaaf23bff2162c5d4f20ce7c237af564d1e7eb8dfeb22e1cdb358af6ca23d8629b848eb41c3414d613e31d84dccd8368d8d20a6931b925af171421ea13ab68883075470893e78b0949622bf173a1ceb1d0575342be4b3c2e7868a0d54215372215d1acaacc3c8fa7d664ba7743692551a9e9539e1c3e7c185d5d5dd8b2652b626262990afc9ffffce7d7d7ddc00d962e1bace729862e65eae6796b567383e5c206eb387ac9e8aeb7be9fa8d639f48f7d82c3dd13b84854ebf6fa18a25816b81b14882363b75140542b2de32c9abbef2224f61c2cedfa506d608a7dc6aae823ea74b8e51026778d61f3aa0368760f45fbf61254fb042321bc1d9d9503247dd92db9f598a6310b6109acd3768196a834339fa82ca387da946118ebdac2cdca1c073d56a0c64a125782b571c252033ee2c2acc9685eda6676cc5c5778e1c2051c3a7408ab57af81bf5f2003d6ad5bb75e6307e92260bd704b0c0ff3814b018b0314375895b48e35760cfbcabbb0bfac0d7bbb4ea1b9eb038cf79dc4e49e717cb8250d77625cf0a1a735ae55b4a1b3f90a36c68fa2b886a856c50de2b58e22d4221b3d3ab2d8e7628b0325ad38b67b0cfbea8f23dd311b5d6b12d016b712710ed9e8a81dc3bac0f533cf0cc6537bcc72c81248a2158de0ab6402e165acc965536d0fd4a50e414d411b916edeb81eea8f166f737c10aa82611d4d88b327a1690a1424af17646ecfd38fc5950a699174f7eeddccf9b133b3b398f3475315fbddc0a2be87d70dfd67d7ade603eb79a0e2052c112eb0260958bd453bd1575081be5d8751d7f22ef9206f61a2a30fb7aa1a702f3e007742cc70373212e3a31f22674d3f523753af750f01916760667f1045fa36e8d197c7fe35c938dc7408c7778f228ba85bbd6330bad6af458679044a0bc65193d944529918bb20cac773df940001d05a4c06d12a96d012116760a125072feb5528deb01bcae4f34cf78cc637db12b12bc409efbbe920554e92018b164395850420cacf47ae138f45817e6b3658d3a9f0bffffbbfd1dcdccc9cb03c3169233c3d3d5feac870f17e2c2eb0e8861df381f5fcc5509925174379552b11b6c7aaaaae218a7504dd5bab7070db561c6ada8be6b6b7d137fa298e74ecc58596bdf83431027722cdf191bb15dede3d8e961da7b02e7e1ce50d379155780d164e471060b10d7bb4e5b1d7de127dc56d44b5c6d1db741c298edbd015e48f4a1b4fa4aced4377c5008cd48d97d47a4ce1e02760a90808c1575603eef2da8c87a2ea2348fcdaaa80326c08c9839e96067679adc2e5dc3538ee6f862b66fa5025c69faa953c014b848f9e7e9805161fbb4b827386556e8f457fbabbbb89c7da828dc949ccd44e4343c3eb30ef3f4d8125c2fea2e86e319c54b8d406bed9602d659299576f351b2c7aee6467328a6481751c5d19f918c94ac4686d3df6ec7b1b1d3d1fe278cf304e3475e0fe968df828d60177c990fe4e621a06066f23237a3f32b65c4263e787700f380153a25ab944b5ba75e5b07ff5468c340fe168e738366f1842839d1f5aedec11ef5f8b9ec63144b9473193ca8b81c57d52734102960c1911da88c9c25f897cd674d5341b0e71212914af27fecdd81d1e365eb819b102edabc2713ddc13359a0acc73148458802d67bf8603d74cc59a6ef4a31eaba9a909e19111f0f6f6c6a64d9b18257b4d8a9536ad58042c8e62f1725e9ba9ee85e7006bae8511bc8025c215c2c2c2d0d4d4444d3531ef474e614f5a360ea74661b2ba04030357d04446809383e770bcb61a1f95e4e3d3959eb81b61843f78bbe2f4e87554a60f62f5aaa3a8697b1f1b33de81b9c3247ccdf3d1ae2583bdb666e8275eebf8ee09ec6d388e54872d68b775c25adb4d68ad3d8292a47208f30b2f0c15bbad858f164949502824080c7a42a27091d28099843484a61e5f0e3d152b54a50d4251561999be1bf0e9466fec8d0ac17bf646d01714800c512ce1e5d31e8b03edf4a62033b78a9c9c9c444f4f0ff1579b101fbf9a18f9d578f4e8d1eb074b9b0d162fe7b599562a99e74a83cf0395f00ca84419b0242424514d3cd6d89133d89b9387b1044f9cac2cc0f0a18b686978076363efe34c75196e5656e1c1ba407cbcc2189f7a9ae1566933f6eebe8a0d917dd8b2fd0aaa77de8593e73198d9f42347cf169ddac4c8af49c1c8ce211c23aa954c4688f5d681c8318b4471de183acbf64353517b71b01868f898da9510090912aa028230139181adac1cc41925631b73723dd6270fb17e69b030b6c6e990d5680f34c1c5b5d168d15526202f87b480c00cf33e7bb7196eb06edebcc9a4c38c8c4c2612131399d1e26b32ef33c1a235ac17e9619fcbb42f6571042f869d05152b18b0aaea313e7916bd95cd188db6c6998a1c1cee3f858ee6f3181afb10e75b1a71b1ac085f6744e3fe0a3bdc0d32c627d131183f720fb9713d48d87096a4c33b58b9f602ccec26e063b6056d6ad2e8b2b7403f19699ee89a4047f5496cb4c94095850f1213f6a3af6904c18e218b7a2b4eca1220e65b98782971e29114c87533317958484a417a390b2c21f25c1ab2e22aa8cf3a022579756c0dcdc207f16ee84d5e83771d4c986abc9210ff33e797e69c6c7c762aa42dc9ad6dad28282cc29a847558b56a350607075f2d58b41f9a016be3b360f1d4c0f70223c1174f81dc6049b1c03a721a077a4f602cd808e777a463bc67147b775d44dfc0fb38df7d00a70bb3f0d5b68db81fed8c8fa20cf0b9bb2dde3e7016cd854711173984c2caf7505c7503b6ce47606c7500b9da66682746be372907936d6338d27904092b7ab1c33c08d17e4de8db3989c2754564a44715e42d1ec0e28708bf00511c21a80b8bc18a7c762a447d24e959ebb9c0a2ca15e3538028df64b8b904e35a58020ea545e36c843fa2e4c5a02828f04c0f3cc763d1b3d87383f58f7ffc036dadade47b4ec686c424e2b37c18057b3d8ac50d969efebc60cd55089d024b46fa959717e6828a06dde2928245cb0d078fddc66090292e1527e26847170607dec181ae777066f024cee425e10f3bb2f1d51a2f7cbec2009f789be26e5621fa0fde45524837366eba84a6ae0f111a7d0ac6b6e3f0334ec34e5509ec7677c540dd011cdf338996d2635869998d709b7cecaa9944f78e1ea82b68b05312df02e6fd2d062ce1e5fc1027b725c975690294343f1f44f9f89852022d2150bf25421e5797374065e628343534d1155e8c235176385a90877dc6eac4c0f3310a37771d6bd733fbbcefdfdf87baba06a6e4101414841d3b7630c0bdf2ca7b221b2cfae5d13d40a9715f3405ca4a73a995fc4b5d1c313f5814249179c0aac3f1e1718c1cbc8891c415b89c1d86534d0dc4bcdec2be9633383afa2e2eecc822be6a2bbe4d0bc417e1a6f830d410f703fd706ae20314afdb8fe8a8099437dec4d6a26bb0769c80b1e51ee46be8a35d471e3d3965384a4cfc64c738e2427be1699c8332e2b3061a0611ee12cea4261640fcf3c0f5164bb54850f32d4a82a6443106283ad213602e59e0893029b368e321e8a89b60fbfa3adc0a72c2787d1d2ed89291a4301f51be3914ab6cee4d41868787515b5b8b88159158bb6e2dd6af5f8fbffef5afafaf8e45bf3cbaa1ec6cb0381d0b4bad59bdf0d48d98e8a26a459beec4c5258879a7608de174593746ea1b707eb5352e3494e3d891eb18683b85b1e1ebb8da5483cb05e978b47505ee47d9e2e3283d3c70b3c48db641ecae3f8795fefb909e7309f51d77e01f7c0a06d6871162108f1655717486446098164c3b2751577c048e9635485d7f0883cd8751915209110131365c74e25860ded121531ee000c636f3f43e7ec6bcf333f789f18912151344b46f1e025c57222136031f84c6e1ec9e0e9cb137838118499ffc0b29d6cc13089c3a758a816b53c62644c74463cd9a35f8ecb3cf5e2d58d4b8732b1607ac97d1c3be905af134d94cc0e2f65773413513ac71dc48c8c4e1c97770324417576ab27072f83446f75fc048df655ce9e9c7b9ec55f8764732be5eed8a075186f8d4d7141fafdb8891897bc808e9444cdc3154b5dc42da967789899f8499590b8a5595d06aac89dec2569c203e6bb4f5306223061115da87038dc3e829ed85959e0d0bac65024ce7c3ecda16f78a1abee5d33528413654cc750a1a814b4c409c80230e17ab68ac0bcf43707034cef9a6e1d6c83e1c77778787ac306404f87906ebf3cf3f474b4b0b0ce8e964b4b5b172e54a1c3f7efc157bac4416581c45a06d2e0a6444c8db48508e1d2fe6ad78292fcc866a2eb04e12b0ee05910f6de42a8eaef5c0f5920462daf7e2e4d15b18d97d14e7462fe05256343e6e28c1f7495ef82acc18774375f085a7332e8f5e454df630c2fdfa9091fb36aa77de86abdf71e8590e235adb1f8d2aa2e85cb501a32d2338b26b0c4d2593880c1d415bc5080ed40d2133268b782841f22513452260318d80cb96cf280b70aae4cfc4721664fc243552c5921090818c101931ea782073cd4e787a7861c23f171f1feec5f1d010c4a8484056907f8e05abcf56dee9cfdffef637e2b1eac83f8919d4d5d5e1e8e888818181570bd686c45496628988cf0bd6bce505191956bce2a99ba580f54960342e17eec4a9965a5c4b72c4bb1d8d387bf60ec63b2671f4f0155cabda8a772bb7e3a72d7ef832d2129f44ebe2bebb05ee163710137f1b6bbc5b11b3ea042a896a6d48bb0423eb49581995a35c51022d3666d85fde8d63bb2719c072361d45d5f6311c203e6b775e274c342dd8e9909f40264ad29d08330f381b24eea9188eefe2d4b168c15546480d72c22a305277407ee21e383ad8612c6807ee0ff7e078cc4a6cd49486ac00ff1c271baf9af7244db4ffddc5c5058a8a8a505151616ebf16c5e28c0a2958f2bc82f58aca0bb3a19a064be419a8586049a2aa868235818f0256e06ed81a9cbd701be722b570ab390f97262ee344ff194cf49dc10da260e7b7aec50fd5497848d2e1c31526b8e767884fc22270faf83d62e27b10ec731059b9ef302b7a1cdd8f41c7ac1f096a56685413c79ee45c8cb48e62bc6d047b6bc750557804fb6b0fe240d5216413d512161061af1f148220bf24496fa2e05f26c40669d9b43a71a7403654d46f8909ca404ecc0c0aa29a305177425e52371c0858a3c165f862a40747e357235d4796a44281196976badcb06b4eb0dadbdbe1e5e5c54045c3dfdf1ffffbbffffbaae60a59e5066ae0e703eb651b765e8aa1f3971816016b8828967f24eebb04e2c2d0795cda1c850f4be3f0cebe1e9c3ff53e8e768ee0f2d8395cce08c7fdce663c4ef1c2c31063dc23007e4abebc5bbb06d0dbf51e625d1ab132ee282a9a6e212ee11c0c2cc7e1a8b71965f222d8e9e488beea03384252eb1031f37b6b47d05bd38fdebac3e8dcb60b5e563eece2e572c66b09f3cb9090260a264cc01260f92b5a89e75bc6068a8f19090a13c32fc62f0649615d28497b40414c0bd606dec85adb0e57774f8cfb6f2760edc364d44aa4ea29429aad589c3948eead22e7028bd6ae020303a1ababcbf82c5b5bdb173e4b186f9577762aa40b133860f1aa54cf9302970e95e89c50090909336055d2543834864f7cc3f1c0d90fefa717e2d2d8386e2698e0fdce1abc7dee039cee1ac189a173b85953802b0d65f8b920100fa31df079b40eee7958e34e6c1219417d8cedf17be0efb50f9b732fa3a0fc1a6c1c27a167da850d4a5aa8529342d7a6128c92d1e044db2486eafa71b0663ff6d51f425fd54154265742434987b54bcc5b34cd0943484096289902513059029818513111a69b81ae62a6214c6e8b115f2529ac0779496f28cb86434e541d1eb6ab911cbb03fe01e11875cfc457137d180f094522014b945d6e58ce56c085ea589ca6bfd0d050062a0d0d0d585b5be3faf5ebafba8e95ca8045bf340e582f7b14b894b6e385c09a09150b2c3aa5535553cf78ac4ffd22f0b9a31f1e7884e0dae5bbb8bada169f34a7e35df2d8c589b771bc7b0cd70647703e6b251e7516e1d13a2f7c176e8a3b213ab866e78ff7bb0ea3a7f35d4439d42166e511920e6f202cea34f4cc47e0aabd1a453202687675c1feaa5e4cec3a822102587ffd000ed40ea2af61187b8b7a901d9703293199a954c54fc011165480a8903a090d880aaa43444099dca70c1141354810959211a51be906405e26162a0a2b202b2483b8c01244f924219a007fcc692d1e1e1fc1a8a717a27514a70aa4b3c19acbbcd39fc78f1f33c5510a969a9a1a4c4d4d71f0e0c157ab589c5121072c5ac37a19d3362fda16b3b85ab1824ee954d5d4e2e4c8383ef38dc003075ffcd1c90f1f54b4e17a570b3ec970c69d9e365cbdf821ceeceec7b9c3e770357f2d3ee8df8b5fb2dcf04d842d3e8bd2c635576f5c5f998133a73ec2d6980ef878ec45c6d68bc8cabb02339b09e81befc43a79155492515947721e066b0730d9710c879a46d05f37885e62e207085c9d396d58179a04092171bcc54e8b74558fb0a01cc485e966b9a69010b382a4981da4249c2023e14da00a21504541416e0dd4e40349aa1347dec64370b6f0466a7239ced985e2c191098c39d9c3575d7e6a4a879fbd5d12f78e7e7381455b6556ac58c1ece540c1d2d222ea5b55f56ae70ae94a1de64b255f1cddba9117b05e65859dbbd78a17b0188f555d8b13c363b8ef1f8ecf1dfcf0b5933fbe0c88c2cd5b1fe1fd95c6b8bfab00d78f5ec4a591d338dd338a5b7bbb71a134074f3ad2f0688d2fbe8f30c3ed50138c5826e04e7b3fd3f5106a5785e8983114555d856fe031e89a0ec159330e3992fca8b1b24077e12e0c13a026da8f60b079948c0e87d0df7218072bfab0337b27e2fcd711332e365d6ee0239e4a807c06c2aa042a33c8483a434eca170ab26150948d82bc7c1c14e5d6434dc611da727a68ccbbc4b4cfd4161cc215074fdc1a3e8a932e56b09293989a84e6068bb307e97cde292b2b8b3953adaaaa2a63e0e9eae857b8fc8bab40ca05d6527cd5ab282ff0aa5653e5069a0a89623d0808c37d920abf720ac077aebeb8d3d64740a9c0837c1fdc39d0856b97eee0fcee1e5c26aaf576763c3e1e3f8c5f32dcf05d983dfe10ad8ba35e6b703a7c1bce9dbc83ec153be1e5bc0b19391790927509c696a3d0336a43a4ac16726585514fbccfdeb25e0cd70c608cfaadd6090cb48ee11081acb7b01b4dd92d88f28a830419292e67778a32a505e2af44f8a52029a245d4ca8680e50f6579a256f209e4722d94c435e161b70e659b0f42494e06fb0b4ee11d57071cef1cc0655f3b688a8ab08bb1ac01c04cb03ae605abbebe1e6e6e6e8c62292b2b33d7fffef7bfbffa7203072c6adc79024b4a92152fb1182abc44a83860d151e169e2a3be245ff6e74e3ef8d22100dfbaf8e3ebb058dcfde01e3e5c65802ff794e0e6f177f036f9cf3fdb3384f777efc4f9ba723ced48c18fab82f038c20aefc579a2ca340f771abbb18f78ad60cb0aac881a4151f9bbf0f29b8481c9102c7512b1565c18b98a92688c5a87eeb21e0c54eec768dd10469bc6304cc03a543580eebc5da8cb6a41844b24a4e80e800c08acbe77415a0ce51784b8a03c512d6b02d40a28282442437105e4052590b1aa176bc332e1e1e9834371fbf04eb83ff61737e364a03d799c6f6a3b49413e7eae3a5639bbbb616eb068bb0cf559142caa5854bdbef9e69b575c6e1079162c5ef60a7dd923c1a5a4c0d98a45c17a18148e2f9cbd493aa43e2b008fdcfcf1f1eefdf8b0b5100f4b4270ef400fdebb7417177777e19de153783b6b15ee1d1fc76f19eef821d2037f8873c0eec832f4fb6dc3dbc76f2227ba1d9e0e3b919e7d1a499bcec3d46a043aa69d705776c15a317e64abc8a03e740d766def20eab517fd3b7a70a8b41703a5fbd15fd0898edc36546d6e44a87d2064f88598b58542eca0fdee2244bda4c5b4a1ac100b25c514682978404b5a1df564446a65ec8cc4a4621c77db8c8f4a72b12b26157bbdac21cecf37b523a0107bd29b17c5a2fb3950b068f59d8265616181b367cfbe4ab0a6cd3b2f60cd778e1be684e54b9d0f5c1258c2ec986dde25d9604de09b90487c4d4cf87da25a5f39f8e3918b1f1e86c6e0e3db1fe10fab8cf0f5de52dc3e761957878fe05cd77edc245eeb6c4d257ed99b8b9fd684e3c768175cce48c1068b7a7c50528fbedef710625982f0b041e4965c865ff024f4cd06a16b54056f5903c48af221595602dbed5cd0b83e0fad051dd85dd28d3dc57bb0777b2776e7ef4673de6e94a554c1cfc4194a02829062ba1a96418c816b19e4c40da0a2980855e514684a1bc1d934023b4bce434a581c9525633867e68d87870e60978b2772ec4d985a18054b6019df0cb0e8628a853cd65ffef297a99121f55974eeb0b3b3f35575374c1748e91748f74fe09cefe645d5ea79a0127901b04e11b0be0d8dc0376e3e241d7a3326fe5ba720fce0416eb7f5e0e38e327c5f18804f0eecc1cd8b77f04ee72e5c1a98c095dc54dc3d790e7fdee2891f5686e1f37591a84fdd8766e77c5c1f3e8bfcb5dd70b5ae4762f231646c390f1bfb61e8991e84b161015c65751120c28f18517e2428c820dbd21115516968ccaa474bc12e125d04ac5da8c96a467ad006981133ae2cc00f593edafbbe1ca2249529c9ba4345693374d4d64251441eabc21b909bd804552505f4e69fc5651b6bdc1c3e8609171b786bab4e4d700b32d341cbe7585738f7aec9b4078bf6bcd3dd6728583425e6e7e7bfbab6196e8f45777ce19cefe67554d81753ab8552e05c603d8a5881ef3d88b7224af599a30fbe2646fe912bf15b44c9ee7ff829eeaf36c1a3ee227c387e12ef8d9dc0a5f60e5c3f3080b3a545783254835fd693d7c7479011631dc29d7a7123ad006343d711615d047fafbdc8de7606b1ab8fc2d46208ba26bd303228818382039c8485e025c80f1fa1e508111745bca61e363b07607b44228a6236614bd07a44983ac2847c2e6a027c90e3a7602d8394900201680d5494b360a81e0e256109e4a54e22d63f115e3ebe185ad58d4b411ee86bd88f6b2b3ca021263235caa43d5d0233b6e3ae5c50b1e8cff6eddb99aa3b058b1a785a8278dea6bf85cffec54c42a74e8dc4e6074b7a1a2c29a9171e052ea6569cebbc80c52a3750b0c6f12872057ef4f6c7b7aefe44b57c19e5face39103f7afae38bba9df87ca0038fb7bae341ef2ebc7ffe36deebda8d8b3d03b85abc05b78e9dc25fb607e1d1fa247c969e8e9d5567906d51850fdafb50993b0837e31244471ec296dc73080a198189d900f48cf7919162034cb537c05cce0a66c4ab5a0a2c872d094702908b88105c8807b4a3a74526f0e912b5522550c9f0d12e5201a812b55253ce84866a260c95dda02fa783e6e2ebb03172c486e45c1c75dd8c4fcbf251b9320ba7a23d9914ba8cbd73b2383399bd8ce754487fe89e5974191867644821a35b1ebd54b098b37f252eae58ac3346c83c0b96a4242b5e41859d57a8a60aa4d52c8ff598fc07fee44b467854b5085c54b5fee81488c76e7ef8c13f180f6e7d802f521cf164770eee0d0ce1c6b10bb8d2d284f70e1dc6b9dc2c7c77bc0f7f4e8ac6d789d9b854db81b8f8339824a3b3f387dfc67adf0ab858546355fc0832b34f22387808a66607a06778003a469dd035a88791ce66182979c24044117afcfc302000191290f40964dac474ab91902770d03e77650963682a6f84b24a1e0cb492a12b63086793107454dd80aca43c4a8afa71c12610df8e0ea1c2c60be5be0e4c9320a705478a4f60ce7eac854e2070f2e4c9674686f7eedd7b15a930650a2ca159607160a2d7e74c815c602d75b79817292fcc990a6b095823e3f8213a123ff907e2895720be2723423a6ff8851349872ec178e2e9873fe616e2ab739378946289873dcdb873ea2a6ef6eec3a5b60e5cadafc2d58387f197ba78fc90928f4ff2aa71b0f72ac2ac0fe256461efaf75d44b8e536b8dad66375dc0832328e212a6a0476f6076064dc0b3d03a25e060430fd26e8e91158d4561058cca0292247d29f1094f805a04046868a42b25097b585a6ea7aa8abe4428d806561b081a439929a028ad05c380971f27775565ec0354777bc3b388983ae8e0834d066f7d5d3d1204da5fc3c35fa71ff7cf5d55708080860e60b693aa4533bcfbb9fc3a21e8baa16072c7a3ab9d960bdcaf9c0e7292fcc3b2a2460fdb4321a4f03fcf194a8d68f9ec1f8a35b204987fef886a4c39fdc02c8fd01f8e2c2db78981f8d9f5b36e2fe9e3df8e0c4bbb8d1528777f70fe1e296247c79f624fe92493c596623ae360fa2b0ee2e726dea70b3b41eed354710609c0967eb2a4485ef4772d22836268e2022bc1fce4e3d3035ee869e7e27b4f576415bb709da3a55d0d3da0a3d8d75d0558b85ae2a09f575d052cf22e92f1feacadba1af950d1bc328a8098923296910c529edd0d65043ffd693b8131e8caeea4ebc1b1b00153111f6e623cb214dd48a7aac65cbe7df1464ae1f5a10a593d13a3a3a0c587484f8bccbee791815b2ca0d8c6211b068a981d7f3dbbccea99bc553e1389ec693541812809f896afde41382471ec1f89298e82f9d7df0a34b107ef2f2c5f709ebf1d5ddf7f1fd7a233cde5b898f874fe2f6a14378a7ae12efede9c2db359578babf083f6716e2d68e833839780df18937b0dbb310576adab1b37214c1a69be164980f6f9766ac08dd8384557d589f308095b1fbe1ebb30736d61dc4d8b7912fb0159ada2dd0d46a20510d6dcd0a686a9441437d07d4d58aa1adb6157666593057f784a698347273df457a5c01bc3dbd31b8b21b5fe467615b54124eaf0e648aab9cad2795f88499a5626fcdb9dbccc2e7d2a15b4752a5e21878ba0bcdab038b7ee142a25051537f6eb09e2705be681ae406eb1451ac27ab56e0d7307ffc1ce88f5ffc43f0d43b94a4c4103c20e9f03b67a2646e41f8d9d7075ff61cc0375da5f8a528100f3b484a3cf60eeeb4d5e3725b3bde2dc8c6471393f8736904becedd8b2bede73172e45304875f41b777212e1596a3a7ed18d612d0dc759261af970547b342783ad522c8a705d161bbb072c51e848776c2d363276c2c9b0864f5d0d3ae838e660d894a12c530d02d80bd652edcad52a02f690c4b7533ec28ba83d88075888a4dc0f1804a7cd3d982742b3734067bb0a785de62564babf389cc58cb386dde772fda67555959096767e729b0e85ef0cf7382019eba1b389577bab12d07acc54ef6bde49a15b3ea86f75ef6a5a7c231fc18178e9fc37cf04b883f7e0d0c2410859094188eaf9d83f13581eb896b209e7a05e0495818bebef711be4fb4c5cf1d5bf159672f6e8f1ec38d8a025ce93d88ab9bd6e2e1c951fc39370eb78bc771eee0fb1838fe10c111d750e3d580f36b36e248fb2076960f2225a414c1e68970d35c0d7b8d04d8e9a6c2c134075e0e6508f4a94344602342fc9be0e7590f0fe74ab83995c1dda9185ecef970b7cb808d36494dc48779dac4a2b1fa1378daf96103f17897bd32f1c9be1e9459d920c6c6647ad5f3723a0810e17931c5ec1fba9511ed20e580450d3c65e1158095fa0c582f1d2a062c11088b8abe74b0a6ca0d23a3f87e25f156611ef839d41bbf0611b80282f1d4270c8fdcc3f115f159dfb9f8e129498fbf12d5faa6a818df5c3a8ea7a93678dc5a8d8f874ee11e4985ef94e4e1bd8e76bc5fb4058f079bf1cbf61c5c2ebb8093a37fc0d0e96fb036f32344bb1ec5feb0024cc4acc7c496220c94b6a2b3b80b95e98d480dcb43a4d56a786846c0412d06b6da0970304a81ab55363c6cb7c29d84abd56672df0658ab87c148421fd6f22a080caa4177e34730d536c396dc56dc745f85638dad1808f58496dc747f972a512b85e582f3ae2b5c0cacab57af92116df08c91213d45ca4b5e4c91c6ae63b1cc3bddc268de51e073eebbc06bbbf1f340c5512c3a097d8a28d637f15e7814e68827a1eef839c487a856004989a1c45b45e05bd7103c24a3c427c4c4ffe21142eef7c397a7cfe2fbea8df897aa78fcb1be011f4f9cc147b525b85c5d83f776e4e3a38632fcb8af143f9554e04cc5bb181fb98f8973dfa1a1ef6b44acbb077fdfab488f3886aa982eb4c594a16b4526f6c624634f7c325ad7a4a36255163607a722da261a9e9a3e705474878d823b01c919563296b090d082938a3e5cb54c909c7f135d9557a026258faad243f8cc3f0a3bf34b7032211c42fcd37bc81bf18b33aa35ff8e7e0b7bac9f7ffe991919d25d7aa86a191919a1b7b7f7f582b5145ff57ce5059117826a0aac6a16585fc5bbe0bb08333c0eb5c3931077fc12ec8bdf0202894245e2078f083c2406fe5b970046b57ef3f5c30f715178f8c98778b2c10abfb614e041eb1e7c32388edbf99b71a9b51def6d4dc4a7cd1578dc5d8e276595b858f91efabb3ec1d1f38f70f8c277e83bf504a5bb1f20b9e013accafc0c31e90fb032ed3e1292dec7c6f863d81ad986f2887cd48427a32c281e79be5148f70c438a473836b805638d833f02748c11e4bf0dd9159fa1a3e818240585b0abf438fe181783dc5589185e1b36b58c4c80f82b2b2129664fad67c1aae42915d24514313131d0d7d767c0a2a507baecfe9598774e81941baca58c009734c9fc9252e0cc0e52da8f3586cf57bae2ab70437c1b668a1f085c4f83ddf15ba01f7e23aaf5b377041ebb1143eee28fc7eebef8d53384dcef816faacbf1eda941fc9a4e94aea60c0f7a47f187ce3db8b1792d2eb677e2fd2d71b85f958b6ffa76e1696521eed49c447fe53dec6e7b8043938f307eee2926df7e8ab1b77fc2d8a59f307ce909062ffe8ade53bfa266fff7d854791f1b0a3e4552ce87e41ff9145647ecc5fad01a6c08cac3bac04c24c475c377e5655454dd455be100648965d85778128f331290ea19849a9880a9cd71a5f90461212839cf39a12b793e756f4e4e0eacacaca69afee2e3e35fd15ca1c84cb05e5669614ea85ec248702ef37e7264027f8876c1e74186f82ad410df851913b86cf19428d7af4101f8cd8fa444cf487ce3124c54cb073fbbfbe34fde640419e28a2fcf9dc6a39a54fca938068f8b77e0c1c0043e27a9f1bda4389c6d6ec5ade2347c92b31a0ff675e3f1ee063caa6bc19dea494c6ebf887d39d7d09e791d4d69efa13ae52aca365e4349f275146fba89d2fc0f51dbf805da7abfc39eb1c7d87bfc293ac69f20b7e94bc46ebe859084eb084e7817159d5fa06ecb39146daa86a69a0a0e654de071ee7a643ab9608d8fc3d4e6b8fa02a23020b16cf97c271bdfcdd3ea1bbacb1f3d7913072cba8875a9fb392cba60955320a55f30dd748d8e085f965acdb567e8cb542b967997624e79727278149fc5b8e1b340637c1e6880af430c88df32c64fa1f6041e4f921283f0ab4f247e24aaf5d0d91f8f5cbdf19b7b2001ce034f56fae3cb7bb7f1649327fe5c958cc7c4db3ce81fc5e7e555b89e1081d3254578afa901f73257e2e3edc47bb575e1f3de01fcf1c020bed93f8caffb26f065ff51dcef3b8d0ffbcfe383031771a3f712ae75bf8dcbed9771b2e6320ee69d4373d2496c5f731a055b6fa0a9eb2bec1dfb0ebd47bf4349c56d74669dc0dae075b0b2b6c6e09a6e7c51b80e3b7cdd6063a0cd755a3a5928f00bccbf3f168f608d8e8e32cbc138069eaa175d8afff25a93895a51b0385ff87c60bd8c14c89876e197ab568c62b1c13a71f8303e5de582cfc2ccf0598011ee07e8e3ab105d7c1f6a4254cb91a8963793129f7aadc0f7ae61042e6ffce4ee837fa17e2bd011df6e598f8777dfc3d375d6f85345367edc924fd2e2201e9454e1767c184eae8bc1b98616dcdad986bb3b4a712bbf0cef1634e342f13e9caa3a8c93cd6771aceb1afe5f7be70155e5b52dea44504410101151912216449a4811a4f726d2114114a98a8820a828288a3596686c892dd6a8316a125ba231b1448d8d622f494c4caca8c939b9ef9e71c61b777c6fad8d1bd1080262ca7d678d31c7ffeff6ef0dfbdbb3acb5e69c3bd69e66c7e632857cb8b99c2d1bcbd9b0e932eb365e61ddba8b7cf0de29762f3ec4ea91dbc874594d48af65147a2e65dbda0a3c4c7be3e91bc8e641a51c1e1bcd82081fda69b6a92e8c2b000ad330a0cd9bf594e39eff72e75d8ef2f2727c7c7c146049ad2537fd7df4d147af611eebc9928eac8dd518b05e654b4c7340f5d414cee0e3cd5b3833d44598c39e022e0b1155990bb8ba09b84c8449b4e46eb0230f02bc79e8132acc609800cb9f1b2e6edcf370e7a1a78820836db83ea7901b0777519560c7fd4902aecc315c59f21e5726cea03c3686fd018eec8cf667f7b85c3e9fb380834b567348c87e71be7b7c1e3b87c7f061b00b9b9c7bb2a14f17de37d76775af0eacb43164a59b15ebe306b14d98d1756bbf61c9eacb0ac8be5c774240758e456f7d4eb7562d898c1bcec7fe63d938cc8765837d155b9915f088483046b3b300ec8d3a7cace23ab3749e1fbffdf69b622e4b19194a47beb1bd76ead9dd501d15d65e2b7c19587f1587fd453ed6ae2d02ac9c08ce25f4a322b2271784a63aefdb9d0bbe267c1b64ca4f4293dd0b71a64af85b55c224de729151a2273707b8f0d043682e6fe18b855872e5bdf95cdbb1967be23a5505a3b89d94c677a573b8327501971246723a208003ce7df8c4de885d361dd96561c0273dbbb2bb7b0ff69bd9b0a7a7359ff6e8c9ce6e267c68d4918d5d7458db518be57aeaccd769c50c6d15e618b7656dd260b6ac3dc8b28d3759238280c94161e8bea9ca888c491cf4496276881def0cf67c92ea25fd2b0d42dae8296a6dd509d6bc450dce70969d2a2450b29e83844b3af4cde863a53c054b7c4912ace723c2e6804afd3582559d622fc0127ec3b951419465fa732ec9898a28610e837a51e163c645bfae2252ecc6ed102b1e0867bd4afa5b1e11fcd03f4c448903b8e5e6cc234f0f1efb3a702fb41717372de3ea87aba98a13b7f3d2b81b3f941f468ee26a490957b28bb81c93cdf9c014ca3d1239eb11cf29cf411c7773e798431f8ef6e9c1b1ee667c29beb4fd263dd86e62c6fb46dd78a77317e6eae9334da72db91a2d19a5d182c2ce6abced62c15bcebd08d254414300346ee27b9c0c88649cb339f362dd6a768c7ab7e940ffd6da75b73c5180f57683c1921d5865cabd9c7d977e966ce4d4987a0e0d880aab67de951aab216035ca043e3319aade6c50b56ad5fa6996ceb46a53583e2a90b23437cea5fb5036acbf70e67b511920c4db944bfe5db81962c2dd506baa023d7824fcad7b6e117cd73f48448acedcf670e0b1a7ab80ae2ff70799707ed3522e7dba957b71fdb93f3281fbc392c56ba3b89914cfb58c242e8d18c2f9c1a15c0874e592a32d17adfb72dec69e73d6fd38646dc79601de2c70f526cfca96e1a626c419e813a4d30e5ff1ff0bd5d0244a4d8d30355522046021022a7395ea329133671da032c497741b33a64438d6c03342cb946eaa6acd0696dcef2ebbafca792cd9ed5eae1f3664aaa261c914cf99425974edf9048957814afd356babda09ab1f6fde4a79ba3f15296e94a7ba7136cd870a0157a5308b15feb2c85a572efb77e2875053e14ff51526d143e16fdd76891060f9f29dab3df74468ff8bb7308d01d63c18d495ca1553283ff225b785bff3303e847b9923b81f1bcf5def607e76f5e5c7011efce8e6c9b71e1e5cebefce19219f440f657e7402a5fdcc59d6538d0f2d54d8dd5b951d3d545863a242b1be2a81eaaa58b76c89632b359cd55a61db521563e14be9696bb178ee192e073a30ccc2982c3fcb9a89d1e2f6bdd0ada3ce6963a71be4d8bb77af6207a9cc8e96a6d0dede5e51bebb79d70a9f4c3728c16a4a71ffe7c1526b53b76ff53ac0da2134d6e9d400ca920728c092722edd9bf2e18e0ab8cafcbb51e663c0e5a08edc9270091fe67180f0adbc06f2934b24dfb9f870d34338eddef6022e611a032da90ad3e7f294048e9f3bc7b725b9540d72e56174300f86c40a4d16c5dd81c257f3f2e3aa97075f6564b3b6a098251ed6ecb76d49797f152afbaa70aa572b3eb73665a797379bddfd586a6cca5cad560c6fa3828dd052e6aa2af412d25980e5e2348079f927b932d096145b53621c8c14e068b56829cca8b9a2dc517381251b09488d25cda1044b4e3934a620db4bc1aa3d8ff522b05e455bbd6ea86a27acee141aeb587200df0c73a36c843b15a91e9449c0327ca91821e08ae8c5395f53cabdf5b916a8cf4f02aeaa907e3cf6f714cefb207e7689168ebc173f785a09475e40e7234c63401faa22daf17db22dc73efb94935f1ce17a61363f4778722bd099efc3fd299b389edd2b3fe0fdc4483e7754e7b2b70adfbbb6e48a5d2b4e0ff2e3e85b1fb06f4d259f6cbacefe5ddf73ecf36fd9b36807d9b6eef8a8a862a1a24257a18964bdf791d9535990be878a582bd21c8d7137ab5e7c3653d5a044af7bcd1ef717825534b55160c9ad3272ca4119199a9b9b2bb6d4bc964c685969a6b9fcaad76d0295a221fc15e963eddabc8d63095e1c1be2ccc9646fcea47a5396ee29440096e54b658a84ab07677d8c28f3d6e36a70076e0f34e541888d80cb9b2a255c6ededcf2b6e69eaf2d8ffd04787e363c0ad7e5a7086d2a27867368ef2ebe3c7395c35f5f66ff8193ec2a9dc2014f03aefaa8f273402b7ef668c535176d4ee6c85d0ffb593ee7386b375f67f5d63bacd8fa88551fff93ad871e73fcc41d9694aec2b2734fb484a9d3d7ebc4ca0f2eb03a791b5fc7f560f400632c3a489fea0ddcd5f5c8d1e9faa4bb6a5d60153fd3afb021436efa539a4259284476ae6826b0526b4ca1fc926a83d5d4d9f53f525b294da19cc7dab9690b27225c381ed597af87ba7122d587d3997e948df4e15c9617e5390154a639521169c659dfce9cf5d6e54a902eb743bbf230d856a1b91e780ce48e73143fbafbf0b3af25f7fd2d791460c32f7ed63c0aeb4c55641b7e0ad3e072b801e7a28469f5d7e6869f002aac25f782db70cfbb0d377c0df86c6429eb5256f2f982ad6c78b79ce269df3121e334d3461e656ac145f28a6e5330e7015b3e7bc4b1c337797ff94ef6ecf89aa205dfb177d47b7c3ab023e33d8de8a4a1aad05209dac60c6eab57672d79e5b699798d986e9063faf4e98ac951e9c02b37fd35b49e43036a903e3585b220c88bc07a99b66a4ce269f39b422d451923598efb648013a7050c27a31c3991e4c189f4204e670553363a80f2d1426be5065139d285f268334ef976e2b497b6804b47c0654855b0158ffc8543ef1eca5de718a17902f929c0967b01122e4b7e0db0e271700f1e46e8713f5a8d7b512db92be47e785baa82f4c4f3da7133c890e343c6722a29976fa696b0f2ad13cc4e58cf21f17f3e9397cffe499b59537094c5f995cc2ffd8171a5b71835fd7b166dbe43e9aa9b246796f3cdc809acf6d261aaaf095a2dabe7b026eaf6c45343ab7e8dd5481f4b0ed91dac5fbf7e8a3df072cac1c1c1811b376e34ff5aa112aca640a5d8375f4f6b92d7a1ad6a978adcb9e103be76771291596fce0a0d733ad249f85bde9cc80ce24c7628653902aab1019ccf0fe6c26857ca63cd382d34d7292f2d2e05b6e587904e225a143e95bfabd05cc1dc75117009c7fe4ea0a3d046bd7918d8875f837af36bb0058f827bf228c49447a146e25c68b2808efc1c68cc8d41e1fc981843c5b864d64cfb8c8db1e3b99213ce57b397b172ee61c6e69d2363f415c68ebe4441ee2d268cbac9ac99f72898fa03a3275c635ae2e75ccb8a63baa31673fdba2ab271e4f2cde28e56d8a8b5f95dab93da60154d16fe5923c192f51ca403afdc4223c192d16233f9584fa71b9a02d69fe55bd55e2b9451a1acf37eb0bf03c7accc39ed642522403bce450ce0f4705f4e6585726a6c046773c3a9cc0fe5e2f850ce8ff5e2dce0ee9cf033e0a48726957e9a7c1faccfed905e3c0870e6be5700775c23b9e715c5bd204f1101f6e141702f1e0599f338c84c1c4d046c46022a43ee061b7367a0335589615ccf0ae083e2edec48c8e4dab8204e2e7a878d8b0e32bdf404efa696b0312a8cf991b1bc973c96695173294838415ed265f2a38eb161d00a2a474433c9bd130b038caa2b2b0b1f6b63e7be18abb6fadde2736db0264f9ad2a03defb5c72fbffca2d8e5200b8548b0e46e52d9a0bcd9d70a65dd86c6ec0a7dd1ced03f1cac27c9143b04581fdbf7e3f35e3d3962d59bd38ed6220274a022dc9573c3fdf966f4204ee5c7716e7c1417260ca2b2701097f203291bd68793819df9da4d8bb35e6d44c4d88edb029abb017db9ebe5cd1df730ee7bc70853194a559803f7c37a51156a269c7e29c63c1828000b77e17142047732fbb3b770051f0dcfe7c7c2602ae74e62cbec9dec5dbb9fcfd287f0fef030063979e0d83b049fde61acc8ca6343f11ce6252c6456c8bb2c8f5dce111f4b92c40f6499004bd1b2f78d16ec32b253740cabafd978e1a4c62de928eb39c8bd58d2144ab8a4e4e7e737af29546a2c25584dd1547f34544f7790cee443e1636db0b3e7a36eddd967de8bc3c2293de1d08f324f272a0789c830c99fd36384d62a4ca062f250ce17c571b1388c4b9384994cefcbc95003be72d716e6b435177d35f83ec0803bfe7db8ebe3c41d2f7feefb46f230643055e10385132fcc65a43d55514e3c8e0c1650455395e1c0c9dc6c36a52ee4664938d766a4b0bb78294767cde1cbf418f67a1a32c1a63345396b98b3e83a9189e788b418c392ee866c0ab4677dda687679f9b0cfd980d8fef6acf53555643e6bb75065bfb1bd6211bade2ef60d4ca6787e4890e41c9632ed3e2c2cac41f51cea5dd2a9edbcd7d6588d75d6ff4cb09413a412ace5d67d5967642ae032636fafde7c6565cb49e1379479b9723ecc938ac440cac74451367918e5d393393f3d910b25d15c280aa322db9593d1267ce1a1c357fd5b735a0076d5a73d3ffa9b8a08d1963b3eeedc0d0aa26a500cbf44c5f36bcc507e894de4d784381ea70ee05aa6379b47ade7f29438be9d379233530a389e9eca1171ffe1fe6d3917da9eb3931239f9e1418e7f70846b5f7dc3f64feeb364dad7fc5452c8753f67be15a6e85c5112a5814e2cf3eca98802f555d4d86f6a479be73a5dd46e2050bd1fabb8ceaac9f58d458b16d5d473900e7c43eb3934b8da4c63c07a1e2ab55a853cfe788da5ad986ed82ac09a6365c322f10f5a6b64c436a1b9f6f6eac3619bbe7cd3df99326f0f2e84fa71312184f3d9719c2f4ea662461ae7678ee042e9102e4c8da07c7cb070f82d39e4d79efdfddbf095b31ae59e1adc107ed88ffe3df929c896db614e3c88f1e6617c100f87f9f130a52fb732acd899b58cb339097cbf3c8f8bd3923933348aa389167ce1a6c3cd103d7e2888e25c7c02df05daf26050377e8db7e1d794506e2c5ecb95ca9f39fc4525078b8b581c1f4b492f6d663b5b28a0315251e733d3beb47eb3eeded3356035d279974336717a3e6be7dcb973cd34f3ae5e3d8f25d3eb9b9271f36769abda3e96046b92a515a59d3ab3d0b00bab8c0dd96a66c25e0b738e88c8e794b32b15fe7e5c0a0fe67242389745f475a12899ca59a3393f378b4bb30560d3e2292f0ee794881abf1ad4957d03dab2cf419d632eada8f0d6e6865c6b143ed54fd1ddb81d6fc6ed6146fc9466c5d18c3cce6485f3ed92542ecf4ae762b21ba7a36dd91fd08ef34e6d795812c39994b1547876e45684310f93ad793cc2865fb2edf86da43b7732e2b9b27c015f7ebc9d3c4b6356da193043fc0864fb945e2d3539606aabc87caedf14367c3f56ed21a7179eafe7207798bef204696d1fab216035d4fcfdd1607d209cf76c4b6bc609b0a608b0e61b19b2d2c498cd3dbab1dbb23747ec4594e8e6ce858020ae4646702d3186ab9943b8583882cad963a89c9fcb8579a3151aac626a2c670b823836cc867d3efaecb06fcd3efb561c1fa0ce457f5dae871bf0ed10436e08402ea6f97025cb919b3342b93a631857d3fb713dd68e834186e23d5bf3387d0097679670c0b6373f240770eb9d591c1d3e84ab71fdb835d48107239cf8c7a4086e789b51d14797f93a1adc0de9ce3b6f952aaaf759b7d2e68b6ed602ac375e0b58fffef7bf090d0dad59339433f0724ffc2bfb58b5f763bd0cac1741f56799c067c12a65cbc66d8ce86345aa4167c68ae8a6d8ac1bf37af7e23dab3e6cb1eecd6e5b0b0e3bda51e6e1c685a010ae46c5722d2989eb19c3b93a2e854bd3b338bf601c958b84cc1f4565e950ca8a223931c69bfdd1ddf9c8b91ddb6d5ab3df499d93fe5a5c88d1e3dab02edcc834e3db3c07ae15f87323cd869f132c44b060c3675e3a5c7533e0d1c24cf6050ce6487400370f1e6473d1264ecd9ccaa17d27397af41a951f7fc20f5312b9ff760e15112250f011da6c562a4b366c51e4123ab4d6e168775b4559c916afc1799743d66fb0b4b4ac49ae484f4f6f061fab1658320bbaa12d74ff0a5029c19a3a6d3a9b85c68a36b72052af2389069d182954fb84ee66ccb2b26485a33ddb06f4678f9b3347dd9d38e7e5c2e5a040ae464bb8467023239d1b39195c9b3c9a0b7372a95c3c9e8ac50554cccb124e7e0267c60fe2ab9401ec0a34668b7d5b763a680a3f4c8fb331469c1fde834b29565c1fde937b49bdb935b83fc7fc4410e0a4cde32c2fbe9d9ccd72734b6eee3bc8eebd97d91f12c2d6759fb3e3d04f7c76e9ff5056f56fca6eff1747f61fe6fc814ff975cf1c7ebd7b9d9c29f3143e969bba2ea7ccad152d7f5f1a1536c1c79263dab4698aee60cac8b021f51c5e9a57283596469bb60a53d858b0ea33817fac292c6593002b404482dedaed08d2ed4044470386763562548f9ec2f7b2647e7f07d60b077e77680047c2023933d097ca307fae4745f06d6292d03699dc1893cdf5fc315c9d92c3a5b902acc51339b7780265734673ba680847c604b13fde814f7c4560e0de95a301dd391b61c19558e1d42738f330c9876b91761cf2e9c4190f437e9e18c636ef817c32268f4b6517583a6d3fbb3cec59f2d6876cdd7f833d87ae7160df794e963fe66cc57df6afdec9bae2e56cf8e2123d6c062826487d353a70a1775fdaabd4dd73baa6e5893085f7ee351eac356bd628fc2c65456539fd201b4cbcf20e520d8dfac17a91a6fab37dabe7c1da289c778f1ee638b56d879b8e2efeed3b32d0a00b838dcd48ee69418e8d1d25ce0358eae7c3d688811c4a8ce3f4d0182ae3c30518e15c4f8ce7db11a9c2b4657363ec58ae8fcfe372712ee767e753b6b090d3f3c7f3cdf40cbece15d1de706f8e463bf3cda0fe5c8874e787383fa1ad82f829c98daf038c39e0de812b43ec291b16c224631b2eee3dc68963154c4dd9c0d2ee5d999b358b95ef1f67e79e0ad6ad2de3e8d5ffcbe53337b89597c55d1763f6f63446ab4575364e645b7dae8b48cd44d1fcb2c54bc16a8ac63a76ecd833a5ba1b52cfa16160098dd5ba0eb01a6b02ff68b894f35812acfedd7b62a5a98d9d80adbfb62e5eedf509e9dc9528939e24f5b625dbc195c99e7ebc15329055b1b17c9c3c8ca3d919948fcee0525a02d793e3f83625896ba9a95c1d399aab39e3b89c3f8e4b857954168bc8af742cc7a78ee2ebbc788ea7047326de978b837df82ede839b43edb9106dca97be861cf632e662d200d63b7a89f78aa0ecc049bedc7b8abc918718a9abc314bf304a0b56b371db69562c3dc6a7dffc37151326f05f2e76fcd3bf0f95411d09b5ec4a0b95168ceba0cf0f761638cac26bf52de934d17997a3aaaa0a7fffc09abd59b254b7ac4af3ca29f65263491064167443c17a997ff5473bef122c07b3eef4d4d0a48fa6267db57418208211bf8e9d0937ecc6901e16a459d993e3eca1c888991533847786a6b0217d247bf3c671bc6832159327702957f85a2387712d730457d2528464725140763e6b0c67c78ce254761a5f8f4ee0684614df24f9533ed8954b717da98c35e5487067bef0efcec9206b0e07b952d0c98a4fa6cfe1e8ce03ecde7c80f96f9733cc2e81707d3dc6fa0f63c1bc4f58b1e22bdedd7a8b23b397f3d0b937bf0ed2e2e1140796c58590a2abc96742c3fdecd08b58dd172f423fd5584d07eb7ffee77f8889895300a52cd53d79f2e45799c77a6a0a9f074bd91cf3af6a029f9f6ed8b4690b76666698b651a787f88c169a1ad86b69e126e00a12ce7cb471378609e73ecbce9102771fa685c6306f8888bed27358933d81cd0593d8377d26c7e7bf4dd98279544e2fe6d2f85c2e8e1d25c01a4165ca70ce258fe0d4b0644e2424702c762027a27d3915edc2d9682b8e47f5605f981dfbfc2df922c4910d960e8cee68cee1e5eff3f9d6cfd8fef67a3eddf2155923f7e0d5b9072146ddc88ecae79d25fb99b5e004dbb75fe0aa431faa06ea73677a2ce533b2a874eec655d7ce022c1b0a0db55fb883f4d999f7b79bdce032373757b1d34159372b2e2eaedea59d0683257d2c995358a3b1ea80eaaf64069fcebccf62e3a60fb0eed68d2eeaea746dad8e8990eee2dc4268b0beda3ab80993122422c5f81ebd18d9d78ef1ee9e4c0b09675ec2089665e6b22a77121b0aa7b36dda1cf6cd5fc291e56bf866d53a4ebfbb8a33f31770baa49853b9591c4f1bcad789311c191cc6e1587fbe8a76e38bd8feec8e71616fac1f1f78dab0d2c5965986b6e49adb73e8adc57cb27637db8ba6b27ef1477cb0ee084307bd234cb60ec1c65614662d64d69c3dbcbdfe3a07924671cbbd330f66a6717149013f4704f07db80e3fb87761559f364fda9dd40556f5ee86c6ec20ad3d649f687777f79ac8d0c9c9a9de7a0e2f2d63a4880a6b81a5347fb53556edf3bf12544fabcdcc10606da5a789191a2aaaa8bd59dd22b7b6c88c6235115969b56c4557019b9d881a232cac18ebe5cbcc98c12c491dc9aa31e3593f612a5ba7ce61c7ac85ec59b88c832bd67078f5460eafddc4e195eb38b468099f4f2f617f7e1e7b46a5b233259e6d89216c1f12ccf6c860a6db5991dfcb82d17ae6e40aadb57bea4c3e9a349fcd0bd7f2fe985cde9fbf95e58b0f13e298464ff17f0db6f4a0a4701dc5259ff3feda137c63db9ddbd9c102ac497c3fb9809ffc8db816d19e9dae6db1d252afc9337c1eacc993a636d914cab16ddbb6df95eaaeaf9e43e3c1d2f87d14d858b0fe8ca870d3c66d989874ab2959fdbb5ff6938c62658158450370e99fa8a96121208b7670644a6434cb33b35837ae90cd93a609c066b373e60276bfb584bd0b970a59ce1ea1cd3e99398f9dc553d9322687752386b35e44981b13e299ebeec620a32e848a683454db90a10666ac2d9ec70e6156366597b0bee41ddecdc866f55b1f307dca7e1c4d06d053041fd1aed14c9ab88ed205a7d832ba940ad76e5c9b379ef2b78bf94e44ac7267ea915043b20c356b5ac93d05eb4d4542c9ab82555959f9ccd28e9c30ddb56bd7abad153eafb15ed50cfe9160294b454a8d656262fabb5f74434545e54d74dab4a19f30a7993e3e2c1c9eccfb39b96cc8cb675341215b0a8bf850989bedc5256c2b2a66eb84f16cce19c3fab45456c50f66b6b717c1264658b4d3a28ff83ff651d7c159538f710363599437954d8101bc3f740cabc6cf62515a162be77f4876fa267a0a002dda762071e048f27337b270d919b67a87723ece85b2f9459c9e37994ba1fe54da1ab0c954178b962d157d0f55856656151ab8a5308fedb4db535c344d80d5741f4b9a3d0996b254b73cd69767d8a0bcc2d705d6ab02d690d72b9df78d9bb6bd12582dde78f369e752f1857510e6c5454036c2cd83e911912c4c1cca52e1b82f1f96c47bc393787768224b62a359101242e18001041a1bd359a30d1d35d43168d31afd566a7451d3c2ba9d1e5931e9cccc98c81a4f77de8dcb64694e21f352c6b06cde0ea283a7d355bd2d96ed8d488b9b485ece66de79fb209bfbf5e36c660427e71672747621474474fab1ad2d530d0d0968a78fb5be218ee636843afb90153490c53367892873d12b75a7975b6894a5baa5d4b766d8a01da44ab0644ea17a13a3c1e680abe9e5b8ab67de5f05acba44555c4fb7756bccf5dae3297ec53156968cb0eb4b9a9d1dc385b9083132a687883e355aaaa2a1d692b6ad5ba1258faaaab45151435b559d6e3a1d89f1896392d058efbab8b038369dc56963782bbd80853377e22dfcad2e2d5b63d9b11ba9830b193d7a330b67ece27d3b5b4ea48673624e31078a32f86cfa443e2a9cc0bafcf122d8c863595a328ba30358123790356fbfc3fcb79a36f3ae1ccb962d5338ed122a65da7d5d593b0d3685adeb01ebf9e6497f4d1febf580a5aca6a7d46632109019c9321068a9a2a2f07164bf67159516a80ab8d484a96a2d028456e2d84a3ea785aaa25daf818616de7d7d18133182a5f6f62c1070cd173eeeec51d3282dfa90fe169118a8b6c6ba630f92a30a1899b98e39a5bb78dbc99d4ffd1cf87aca38be289dc4ee4963f9282f9d6d39e96c1151ea0e61967797888873de8257328572ac5ebdfa9952ddd2cfba7efd7ad3ca18d59ec77a1d1aabb93453fd60554785c62626af09ac979851994dd3a2850230e9fbb454157e908aaa2240503e2efda276ea9a38f67621d3379c85b676cc152672cee0246665cf6672fe07f4ed164087966af4ee604c9c7f1aa9498b299eb287e9437299ddc7820d036cf8382e84bde9f17c9c9ec096d860e6b95831bc8729338b4b85296cfa74831c5f7ef9654d64a84caea8ab205b8332a19560c90c9dfa66d9d55eb3296cf2ee86923f17ac67205379224f60ab79ac457564aaada689b5993d29fdbd986bd38fd9516994c60c61c6e8598ccf5d8fad804b5f682e53cd0e043a84921451ccc8b4354c9cb49d0929b3c8f18d27c3de8be1d6ae2438069118944656ea02162c58a748ff7a15b06edfbefdbb52ddb367cf6e1a58b5a34285c66a266df5476e4d6e0ee7fd7568b0ba1e6ba3d69aee467d186aedc40c1b3ba64525333d3c96928c12268f5d8fb3551c066a1a7414d2a7536f829ca2890dc825317a2ea9c35630327d2d99196b4819b69cc111b309f51ac3f492f9d56de5ee371d2cb955263c3cfc9952dd1919198d3785cff858021ea9b19ac3bfaa2f59b5b961ab0d96f15f04ac8680a7264c9eb1416fe27ad930cd52c8c0044ac2a2281a9ac7d4823584fa4cc058cb98f622009080f56867443f137b5c2dbc71b70e121248ff5e5ed81839d0b3732f8a274f655e13f763d51ea3468d7a266bc7dbdb5bb196d870b06eddaa014bce0535c41436352aac0faee79fd7e445e8bf88296c305c326750448c86fa26849b9a53686e49b17f1c53c3229818994449ee3b640a1fab9f7938066ddaa1abd20a5d6122dbb7d2444f9853bdd69ae8b6d2a05d4b75f4b57599fc64a3dfab98423966ce9cf9ccd28eacedf0a26bbebc334503c07a1533f82260ea02ab3ee7be2ef06a8365f237d158353e976299491d3d9d2e781af620b75b2f26ba0552141ac5a480100a13739992b39461b16fe1629340375d0bf45bb7a39d6a2bda89485321aa6a18ea74a2a8a8b4c15593eb1b9b366daa71e0954d9c0e1f3edcc8a830a97a115a82a5a6dee685a65009567301f6aa5aeff9fb94606d5068acbf1158b5cca2aa30773aea7a58e91b92626842aea53d13fc0631597cc1e3030229189cc394acf9e4a72d6258f42c06f916e2e7321a6fc79104b88e252eb498f973965783f58a1aabacacec991af072ca414e4334bedaccb0a76029720a9bc1c76aaa36abeffebab459ede90693bf21584fe16a85869a1686da1df1efd8993401584e3f3726fa8432c1c79b02770ff282a2c81751647ec278260c9fca84e46914244d2127369705b31689c870d12b9b42b9b4230bddca6c1d0996ccde99316346d3a242852914e0bc08ac3f4353bd08b2ba1ed712916cf504e996bf2d58b523c956c28fd26ead4d0fe1378576d027a9ab2999364ee4b9f993efe14bbeeb00f29dedc875b225c75188bd9538dab0706a49a30bafd535468c1841efdebd6b2a2ae7e5e5357682b43a13ba3658756df0536aad3fcb24d625122c9909bd496a2cd3bf2f58358be10aedd552e1d8b7154e7a37cdb6786ab72752af13838dcd1966d18f51766e8c767427cb610059fd9cc9b07765de94194dced27991032ffbebc8651d3d3d3d05684d6a20500396ac32f33705ebef6c0a5facbdde54cce2b712ce796b21ede57aa5f89e64b2887bdbf678e9e8e1a5ab8f477b7d061874a14446850b16350b587bf6ec514486b2b940bb76ed888989694254f84463a9bf04ace781faab00f6bf112ce5faa4621d52019a8a42545aa82ab6f7b416e79aaaaab46fa9869edc45a1adc3e4a26216cc6f1eb0644b1439032f3596b6b636d1d1d18da9f37ebb26135af349211009567d5b665ea4bdfe6c6d56bda433bd7a1ecbd8e47f11580dd76e4f0baf491fab8ae61873e7ce55ecc9d2d2d27a61a7fb37ea5e17baf32c584f345643c07a19600d9d8d6f0e91d79d5c34e53f6009b0e6ce59c8c3878f9a052ca9f964e94809d6dab56b1b0e968c1e525232491a9af28c8fd518b05ea6b55ea6dd5e065b434cb08c10f3f2f2d9b861cbff3253d878b04a4b6735a8b655632ad1c8259e462de9fcf3b77f3236378fe1c2cfd26a5bbd5da6cd2b80d59cf222b0ea9bb41d3c78886265dfccacbb62ebcaff2f7029a728a41f545c345dfcc0c6bdb4e64263474949c90b9b37bd515ffdc9e2e262068646d2d5c80c834e5d15a522b575daa3a5ad8bb6563bf1816b8bae62ebb23c6ad71c9fbb4febd9e7cbdb5ab56fd7bcf659d1d2fafd7dcad7d69667debbe6353a787878111414428fee3dd0d5d555a86ff92b9622130d94a2b84f53b35ada3ebdaffa71cd1a69db56f399d7571fb59eb9d6d3e73efb3ef2b5cf5eab6dadebd57ebef27d9edef7bc28ae27afab55eb3d14cf7ffaf7e9e8e82ae69afcfc0258b56a0dcd3d6499a317e517be51df8be4ee40e9fd3bf577c5c73788e090080685c71211395821910a89571ca3a28688e820917071ae9028798c53488490eae73d959ac7a3e26b5d6b70cde3118aeb3c398fa8f55854f57978addb118aeb0cae7e2cead96b4548119f2d2363246e6eee8a5287d23770b07712c767c5d1b13fae6e9e840d8ac4c5c54ddc76c6c9c959717f7d229fe354fbbce6354e2fb886d333af7b7adba946aa3f8b531deff7f439a1a16178fbf8557f7ef1f7d82ba4bf3877144747faf573c0d6d64ef13797969636b8f87f738c375ef6843b77eeb078f162929393898d8d23212141d124518a3c1f3e7cb8a2154641e12466cc99ab982c4b96923c429ca7d4886c9fa194d4d45421698aa38c28a4c89a4b4a917b7cea12f9b87c7ef5355215d753bca7f87cd59fa5faf32865685212438726919838949c9c1cb2b3b3993871220505058c1b374e51bc558abc2d459131bce06d4582a6744a656322a5c882fab56fbf48e4735e24ebd7afffdd7db55f27df4b56759122d7de6acbaa55ab1452fb5cca277b76b36ddb7656ae5cada8a5203fb38cd66437d4152b56b074e952b66eddaaf0851a5290f60f05ab76fefebffef52fc55a9174ec2570f7eedde39ffffc27fff8c73ff88738fef6db7ff10fe11c4a0751def76bad73a53c7ffb4522af5997bcecb5b5df53f91ae57d4a91f330ca6bd5feacf2f89bb8ff5ffffdaf173aa4ff19af01acff8cff8cff80f59ff1a78fff0733b17e3822111efc0000000049454e44ae426082, 1);

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
('hello', NULL, 2, 4),
('nhanvien', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 5),
('thanglong', NULL, 2, 3);

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
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `hoadonban`
--
ALTER TABLE `hoadonban`
  MODIFY `sohd_Ban` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  MODIFY `sohd_Mua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `ma_KH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `ma_NCC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `ma_NV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  MODIFY `ma_nsx` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `ma_PhanQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `ma_SP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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