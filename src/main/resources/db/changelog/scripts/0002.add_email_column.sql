--liquibase formatted sql
--changeset samueljuniior:2 - adicionando campo email

ALTER TABLE TB_USUARIO
    ADD COLUMN EMAIL VARCHAR;
