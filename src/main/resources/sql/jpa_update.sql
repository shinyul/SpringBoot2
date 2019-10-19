ALTER TABLE abdata.as_is_data ADD FULLTEXT INDEX t_as_is_data_c_product_name_kr_ft_index (product_name_kr);

OPTIMIZE TABLE abdata.as_is_data;