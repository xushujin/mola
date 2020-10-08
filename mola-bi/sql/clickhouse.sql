CREATE DATABASE IF NOT EXISTS wisdom_cloud_460101;

create table if not exists t_bill_asset_bill
(
	id String,
	resident_name Nullable(String),
	mobile Nullable(String),
	resident_id Nullable(String),
	house_id Nullable(String),
	house_name Nullable(String),
	unit_id Nullable(String),
	unit_name Nullable(String),
	build_id Nullable(String),
	build_name Nullable(String),
	community_id Nullable(String),
	community_name Nullable(String),
	parking_lot_id Nullable(String),
	parking_lot_name Nullable(String),
	asset_id String,
	asset_name String,
	bill_type String,
	fee_type String,
	charge_subject String,
	charge_standard_id String,
	identification String,
	fee_name String,
	quantity Nullable(Decimal(12,2)),
	price Nullable(Decimal(32,16)),
	unit Nullable(String),
	receivable_fee Nullable(Decimal(30,2)),
	discount_fee Nullable(Decimal(30,2)),
	received_fee Nullable(Decimal(30,2)),
	arrears_fee Nullable(Decimal(30,2)),
	arrears_notice_at Nullable(DateTime),
	arrears_notice_num Nullable(Int32),
	charge_year_month String,
	formula Nullable(String),
	convert_formula Nullable(String),
	audit_status Nullable(String),
	freeze Int8,
	advance Int8,
	deleted Int8,
	remark Nullable(String),
	create_at DateTime,
	create_by Nullable(String),
	update_at Nullable(DateTime),
	update_by Nullable(String)
)
engine = MergeTree ORDER BY id
SETTINGS index_granularity = 8192;
insert into t_bill_asset_bill SELECT * FROM mysql('127.0.0.1:3307', 'wisdom_cloud_460101', 't_bill_asset_bill', 'root', 'it_password');


CREATE TABLE t_bill_asset_bill
ENGINE = MergeTree
ORDER BY id AS
SELECT *
FROM mysql('127.0.0.1:3307', 'wisdom_cloud_460101', 't_bill_asset_bill', 'root', 'it_password');


CREATE TABLE t_bill_pay_detail
ENGINE = MergeTree
ORDER BY id AS
SELECT *
FROM mysql('127.0.0.1:3307', 'wisdom_cloud_460101', 't_bill_pay_detail', 'root', 'it_password');