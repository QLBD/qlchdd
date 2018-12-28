DROP DATABASE IF EXISTS qlchdd;

--
-- Database: `qlchdd`
--
CREATE SCHEMA IF NOT EXISTS `qlchdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `qlchdd` ;

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Nam` ()  NO SQL
SELECT Year(hoadonban.ngay_Ban) AS Nam, SUM(hoadonban.tongtien_Ban) AS TongDoanhThu
	FROM hoadonban
	Group by Nam$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Ngay` ()  NO SQL
SELECT Day(hoadonban.ngay_Ban) AS Ngay, SUM(hoadonban.tongtien_Ban) AS TongDoanhThu
	FROM hoadonban
	Group by Ngay$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Thang` ()  NO SQL
SELECT Month(hoadonban.ngay_Ban) AS Thang, SUM(hoadonban.tongtien_Ban) AS TongDoanhThu
	FROM hoadonban
	Group by Thang$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HDBan_NgBan` (IN `NGBAN` DATE)  NO SQL
SELECT * FROM hoadonban WHERE hoadonban.ngay_Ban = NGBAN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HDMua_NgNhap` (IN `NGNHAP` DATE)  NO SQL
SELECT * FROM hoadonmua WHERE hoadonmua.ngay_Nhap = NGNHAP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `KhachHang` (IN `TenKH` VARCHAR(45))  NO SQL
SELECT * FROM khachhang WHERE ten_KH LIKE CONCAT('%',TENKH,'%')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `NhanVien` (IN `TENNV` VARCHAR(45))  NO SQL
SELECT * FROM nhanvien WHERE ten_NV LIKE CONCAT('%',TENNV,'%')$$

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
	year(hd.ngay_Ban) = nam and sp.tinhtrang = 1
GROUP BY sp.ma_SP, sp.ten_SP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongQuy` (IN `quy` INT, IN `nam` INT)  NO SQL
SELECT sp.ma_SP , sp.ten_SP , SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam and sp.tinhtrang = 1 and quarter(hd.ngay_Ban) = quy
GROUP BY sp.ma_SP, sp.ten_SP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongThang` (IN `thang` INT, IN `nam` INT)  NO SQL
SELECT sp.ma_SP , sp.ten_SP , SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam and sp.tinhtrang = 1 and month(hd.ngay_Ban) = thang
GROUP BY sp.ma_SP, sp.ten_SP$$

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
-- Cấu trúc bảng cho bảng `baohanh`
--

CREATE TABLE `baohanh` (
  `ma_BH` int(11) NOT NULL,
  `sohd_Ban` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `serial` int(11) NOT NULL,
  `yeucau_BH` varchar(45) NOT NULL,
  `ngaynhan` datetime NOT NULL,
  `tinhtrang` int(11) NOT NULL,
  `ngaytra` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Bẫy `baohanh`
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
-- Cấu trúc bảng cho bảng `cthd_ban`
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
-- Đang đổ dữ liệu cho bảng `cthd_ban`
--

INSERT INTO `cthd_ban` (`sohd_Ban`, `ma_SP`, `sl`, `ma_KM`, `gia_Goc`, `tien_Giam`, `thanhtien`) VALUES
(7, 1, 3, NULL, 7000000, 0, 21000000);

--
-- Bẫy `cthd_ban`
--
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
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cthd_mua`
--

CREATE TABLE `cthd_mua` (
  `sohd_Mua` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `sl` int(11) NOT NULL,
  `dongia_SP` double NOT NULL,
  `thanhtien` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `cthd_mua`
--

INSERT INTO `cthd_mua` (`sohd_Mua`, `ma_SP`, `sl`, `dongia_SP`, `thanhtien`) VALUES
(13, 1, 3, 5000000, 15000000);

--
-- Bẫy `cthd_mua`
--
DELIMITER $$
CREATE TRIGGER `insert_CTHDM` BEFORE INSERT ON `cthd_mua` FOR EACH ROW BEGIN
	DECLARE tongtien INT;
    
    SELECT hoadonmua.tongtien_Mua INTO tongtien FROM hoadonmua WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
    
    SET NEW.thanhtien = NEW.sl*NEW.dongia_SP;
    
    SET tongtien = tongtien + NEW.thanhtien;
    
    UPDATE hoadonmua SET hoadonmua.tongtien_Mua = tongtien WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
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
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctkm`
--

CREATE TABLE `ctkm` (
  `ma_KM` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `ctkm`
--

INSERT INTO `ctkm` (`ma_KM`, `ma_SP`) VALUES
(1, 3),
(4, 1),
(4, 2),
(4, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doanhthu`
--

CREATE TABLE `doanhthu` (
  `thang` int(11) NOT NULL,
  `nam` int(11) NOT NULL,
  `tienban_SP` double DEFAULT '0',
  `tienmua_SP` double DEFAULT '0',
  `tienluong_NV` double DEFAULT '0',
  `tienloi` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `doanhthu`
--

INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES
(12, 2018, 21000000, 15000000, 634123, 5365877);

--
-- Bẫy `doanhthu`
--
DELIMITER $$
CREATE TRIGGER `insert_doanhthu` BEFORE INSERT ON `doanhthu` FOR EACH ROW BEGIN
	DECLARE luong DOUBLE;
    SELECT SUM(nhanvien.luong_CB) INTO luong FROM nhanvien WHERE nhanvien.tinh_trang = 1;
    SET NEW.tienluong_NV = luong + NEW.tienluong_NV;
    SET NEW.tienloi = NEW.tienban_SP - NEW.tienmua_SP - NEW.tienluong_NV;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_doanhthu` BEFORE UPDATE ON `doanhthu` FOR EACH ROW BEGIN
    SET NEW.tienloi = NEW.tienban_SP - NEW.tienmua_SP - NEW.tienluong_NV;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadonban`
--

CREATE TABLE `hoadonban` (
  `sohd_Ban` int(11) NOT NULL,
  `ngay_Ban` date NOT NULL,
  `ma_NV` int(11) DEFAULT NULL,
  `ma_KH` int(11) DEFAULT NULL,
  `tongtien_Ban` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hoadonban`
--

INSERT INTO `hoadonban` (`sohd_Ban`, `ngay_Ban`, `ma_NV`, `ma_KH`, `tongtien_Ban`) VALUES
(7, '2018-12-28', 3, 1, 21000000);

--
-- Bẫy `hoadonban`
--
DELIMITER $$
CREATE TRIGGER `insert_hoadonban` AFTER INSERT ON `hoadonban` FOR EACH ROW BEGIN
	DECLARE flag1 INT;
    DECLARE flag2 INT;
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienhh DOUBLE;
    DECLARE hs DOUBLE DEFAULT 0.05;
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
-- Cấu trúc bảng cho bảng `hoadonmua`
--

CREATE TABLE `hoadonmua` (
  `sohd_Mua` int(11) NOT NULL,
  `ngay_Nhap` date DEFAULT NULL,
  `ma_NCC` int(11) DEFAULT NULL,
  `ma_NV` int(11) NOT NULL,
  `tongtien_Mua` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hoadonmua`
--

INSERT INTO `hoadonmua` (`sohd_Mua`, `ngay_Nhap`, `ma_NCC`, `ma_NV`, `tongtien_Mua`) VALUES
(13, '2018-12-28', 2, 2, 30000000);

--
-- Bẫy `hoadonmua`
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
-- Cấu trúc bảng cho bảng `hoahong`
--

CREATE TABLE `hoahong` (
  `ma_NV` int(11) NOT NULL,
  `thang` int(2) NOT NULL,
  `nam` int(4) NOT NULL,
  `tien_HH` double(255,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hoahong`
--

INSERT INTO `hoahong` (`ma_NV`, `thang`, `nam`, `tien_HH`) VALUES
(3, 12, 2018, 630000);

--
-- Bẫy `hoahong`
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
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `ma_KH` int(11) NOT NULL,
  `ten_KH` varchar(45) DEFAULT NULL,
  `soCMND_KH` int(11) DEFAULT NULL,
  `diachi_KH` varchar(45) DEFAULT NULL,
  `soDT_KH` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`ma_KH`, `ten_KH`, `soCMND_KH`, `diachi_KH`, `soDT_KH`, `email`) VALUES
(1, 'Nguyễn Văn Hậu', 321643275, 'Bình Thạnh', 348249328, NULL),
(2, 'Luis Alberto Suárez Díaz', 56789, 'Uruguay', 13456, NULL),
(3, 'Edson Arantes do Nascimento', 6789, 'Brazil', 5432, NULL),
(4, 'Vincent Willem van Gogh', 3444, 'Hà Lan', 333333, NULL),
(5, 'William Sydney Porter', 32156, 'Mỹ', 777887, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `ma_KM` int(11) NOT NULL,
  `ten_KM` varchar(45) NOT NULL,
  `hs_KM` float NOT NULL,
  `ngay_BD` date NOT NULL,
  `ngay_KT` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`ma_KM`, `ten_KM`, `hs_KM`, `ngay_BD`, `ngay_KT`) VALUES
(1, 'Quốc Khánh', 0, '2018-09-02', '2018-09-02'),
(2, '30/4 - 1/5', 0.1, '2018-04-29', '2018-05-01'),
(3, '20/11/2018', 0.05, '2018-11-19', '2018-11-20'),
(4, 'Noel 2018', 0.07, '2018-12-23', '2018-12-25'),
(5, 'Tết dương lịch 2019', 0.15, '2018-12-29', '2019-01-01');

--
-- Bẫy `khuyenmai`
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
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `ma_NCC` int(11) NOT NULL,
  `ten_NCC` varchar(45) NOT NULL,
  `diachi_NCC` varchar(45) NOT NULL,
  `soDT_NCC` int(11) NOT NULL,
  `tinh_trang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`ma_NCC`, `ten_NCC`, `diachi_NCC`, `soDT_NCC`, `tinh_trang`) VALUES
(1, 'Alpha', 'Quận 2', 23456, 1),
(2, 'Higa', 'Gò Vấp', 19567, 1),
(3, 'LaLa', 'Quận 9', 666777, 1),
(4, 'Syb', 'Bình Chánh', 5656, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
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
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`ma_NV`, `ten_NV`, `soCMND_NV`, `gioitinh`, `ngaysinh_NV`, `diachi_NV`, `soDT_NV`, `ngay_VaoLam`, `luong_CB`, `tinh_trang`) VALUES
(1, 'thang', 147258369, 1, '2018-12-06', 'sdfsadfsd', 926528212, '2018-12-07', 123, 1),
(2, 'Hồ Thái Thăng', 123456789, 1, '3898-02-01', 'ko có', 123456, '3918-11-01', 1000, 1),
(3, 'Hồ Thái Thăng', 123456789, 1, '3898-02-01', 'ko có', 123456, '3918-11-01', 1000, 1),
(4, 'Loui Pasteur', 345345, 1, '1822-12-27', 'Pháp', 123456, '2018-11-01', 1000, 1),
(5, 'Thomas Alva Edison', 7878, 1, '1847-02-11', 'Mỹ', 567432, '2018-01-01', 1000, 1);

--
-- Bẫy `nhanvien`
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
-- Cấu trúc bảng cho bảng `nhasanxuat`
--

CREATE TABLE `nhasanxuat` (
  `ma_nsx` int(11) NOT NULL,
  `ten_nsx` varchar(45) NOT NULL,
  `thongtin` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhasanxuat`
--

INSERT INTO `nhasanxuat` (`ma_nsx`, `ten_nsx`, `thongtin`) VALUES
(1, 'ABC', ''),
(2, 'XYZ', ''),
(3, 'GHI', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `ma_PhanQuyen` int(11) NOT NULL,
  `quyentruycap` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`ma_PhanQuyen`, `quyentruycap`) VALUES
(1, 'admin'),
(2, 'banhang');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
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
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(1, 'iPhone 6 32GB', 1, 7, 2017, 7000000, 12, 'Trung Quốc', 'Hồng', '32G', NULL, NULL, 1),
(2, 'Samsung Galaxy A9', 3, 10, 2018, 12500000, 12, NULL, 'Đen', '128GB', '6.3 inch Full HD+ (1080 x 2220 Pixels)', NULL, 1),
(3, 'Samsung Galaxy S8', 2, 8, 2018, 16000000, 12, NULL, 'Xanh', '64GB', '2K+ (1440 x 2960 Pixels)', NULL, 1),
(4, 'Oppo neo 9', 1, 5, 2017, 5000000, 12, NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ten_DangNhap` varchar(45) NOT NULL,
  `matkhau_DangNhap` varchar(45) DEFAULT NULL,
  `ma_PhanQuyen` int(11) DEFAULT NULL,
  `ma_NV` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`ten_DangNhap`, `matkhau_DangNhap`, `ma_PhanQuyen`, `ma_NV`) VALUES
('admin', 'C31F804A0E4A8943A7A5577A292F2321', 1, NULL),
('nhanvien', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 5);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baohanh`
--
ALTER TABLE `baohanh`
  ADD PRIMARY KEY (`ma_BH`),
  ADD KEY `sohd_Ban` (`sohd_Ban`),
  ADD KEY `ma_SP` (`ma_SP`);

--
-- Chỉ mục cho bảng `cthd_ban`
--
ALTER TABLE `cthd_ban`
  ADD PRIMARY KEY (`sohd_Ban`,`ma_SP`),
  ADD KEY `fk_CTHDB_KM` (`ma_KM`),
  ADD KEY `fk_CTHDB_SP` (`ma_SP`);

--
-- Chỉ mục cho bảng `cthd_mua`
--
ALTER TABLE `cthd_mua`
  ADD PRIMARY KEY (`sohd_Mua`,`ma_SP`),
  ADD KEY `fk_CTHDM_SP` (`ma_SP`);

--
-- Chỉ mục cho bảng `ctkm`
--
ALTER TABLE `ctkm`
  ADD PRIMARY KEY (`ma_KM`,`ma_SP`),
  ADD KEY `fk_CTKM_SP` (`ma_SP`);

--
-- Chỉ mục cho bảng `doanhthu`
--
ALTER TABLE `doanhthu`
  ADD PRIMARY KEY (`thang`,`nam`);

--
-- Chỉ mục cho bảng `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD PRIMARY KEY (`sohd_Ban`),
  ADD KEY `fk_hd_kh` (`ma_KH`),
  ADD KEY `fk_hd_nv` (`ma_NV`);

--
-- Chỉ mục cho bảng `hoadonmua`
--
ALTER TABLE `hoadonmua`
  ADD PRIMARY KEY (`sohd_Mua`),
  ADD KEY `fk_HDM_NCC` (`ma_NCC`),
  ADD KEY `ma_NV` (`ma_NV`);

--
-- Chỉ mục cho bảng `hoahong`
--
ALTER TABLE `hoahong`
  ADD PRIMARY KEY (`ma_NV`,`thang`,`nam`),
  ADD KEY `ma_NV` (`ma_NV`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`ma_KH`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`ma_KM`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`ma_NCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`ma_NV`);

--
-- Chỉ mục cho bảng `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD PRIMARY KEY (`ma_nsx`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`ma_PhanQuyen`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`ma_SP`),
  ADD KEY `ma_nsx` (`ma_nsx`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ten_DangNhap`),
  ADD KEY `fk_TK_NV` (`ma_NV`),
  ADD KEY `fk_TK_PQ` (`ma_PhanQuyen`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `baohanh`
--
ALTER TABLE `baohanh`
  MODIFY `ma_BH` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `ctkm`
--
ALTER TABLE `ctkm`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `hoadonban`
--
ALTER TABLE `hoadonban`
  MODIFY `sohd_Ban` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `hoadonmua`
--
ALTER TABLE `hoadonmua`
  MODIFY `sohd_Mua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `ma_KH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `ma_NCC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `ma_NV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  MODIFY `ma_nsx` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `ma_PhanQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `ma_SP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `baohanh`
--
ALTER TABLE `baohanh`
  ADD CONSTRAINT `baohanh_ibfk_1` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`),
  ADD CONSTRAINT `baohanh_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`);

--
-- Các ràng buộc cho bảng `cthd_ban`
--
ALTER TABLE `cthd_ban`
  ADD CONSTRAINT `cthd_ban_ibfk_1` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`),
  ADD CONSTRAINT `cthd_ban_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `cthd_ban_ibfk_3` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`);

--
-- Các ràng buộc cho bảng `cthd_mua`
--
ALTER TABLE `cthd_mua`
  ADD CONSTRAINT `cthd_mua_ibfk_1` FOREIGN KEY (`sohd_Mua`) REFERENCES `hoadonmua` (`sohd_Mua`),
  ADD CONSTRAINT `cthd_mua_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`);

--
-- Các ràng buộc cho bảng `ctkm`
--
ALTER TABLE `ctkm`
  ADD CONSTRAINT `ctkm_ibfk_1` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `ctkm_ibfk_2` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`);

--
-- Các ràng buộc cho bảng `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD CONSTRAINT `hoadonban_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `hoadonban_ibfk_2` FOREIGN KEY (`ma_KH`) REFERENCES `khachhang` (`ma_KH`);

--
-- Các ràng buộc cho bảng `hoadonmua`
--
ALTER TABLE `hoadonmua`
  ADD CONSTRAINT `hoadonmua_ibfk_1` FOREIGN KEY (`ma_NCC`) REFERENCES `nhacungcap` (`ma_NCC`),
  ADD CONSTRAINT `hoadonmua_ibfk_2` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`);

--
-- Các ràng buộc cho bảng `hoahong`
--
ALTER TABLE `hoahong`
  ADD CONSTRAINT `hoahong_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`ma_nsx`) REFERENCES `nhasanxuat` (`ma_nsx`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`ma_PhanQuyen`) REFERENCES `phanquyen` (`ma_PhanQuyen`);
COMMIT;