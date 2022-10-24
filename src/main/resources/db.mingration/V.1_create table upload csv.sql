CREATE TABLE upload_csv_file (
  `product_name` varchar(512) DEFAULT NULL COMMENT 'Product Name',
  `sku` varchar(512) DEFAULT NULL COMMENT 'Sku',
  `price` decimal(65,0) DEFAULT NULL COMMENT 'Price',
  `images` varchar(512) DEFAULT NULL COMMENT 'Images'
);
