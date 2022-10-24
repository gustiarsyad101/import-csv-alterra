CREATE TABLE web_job (
    id bigint(20) NOT NULL primary key auto_increment,

    merchant_id bigint(20) NULL DEFAULT 0 COMMENT 'Merchant Id',
    job varchar(255) NULL COMMENT 'Job type',
    job_status tinyint(1) NULL DEFAULT 1 COMMENT 'Job Status',
    job_file varchar(512) NULL COMMENT 'Job Filename',
    job_progress tinyint(1) NULL DEFAULT 0 COMMENT 'Job Progress',

    status tinyint(1) NOT NULL DEFAULT '0',
    created_by varchar(36) NOT NULL,
    updated_by varchar(36) DEFAULT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `web_job` ADD INDEX `merchant_id_index` (`merchant_id`);
ALTER TABLE `web_job` ADD INDEX `job_index` (`job`);
ALTER TABLE `web_job` ADD INDEX `job_status_index` (`job_status`);