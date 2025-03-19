# Spring-Boot project detail

Run project to run project
 mvn spring-boot:run

 Url for filter :
    Brnad 
    - localhost:8080/brands/1/models
    - localhost:8080/brands?_limit=5&_page=0

    Product
    - localhost:8080/products?minPrice=1500&maxPrice=2000
    - localhost:8080/products?salePrice=1000
    - http://localhost:8080/products?name=pone&modelId=1&minPrice=1000&maxPrice=2000
    - http://localhost:8080/products/highest-price
    - http://localhost:8080/products/lowest-price

 
  To drop all table in Pgamin :
  DO $$ DECLARE 
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
        EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END $$;
