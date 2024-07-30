package beyond.ordersystem.product.service;

import beyond.ordersystem.product.domain.Product;
import beyond.ordersystem.product.dto.ProductSaveReqDto;
import beyond.ordersystem.product.dto.ProductResDto;
import beyond.ordersystem.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductSaveReqDto dto) {
        MultipartFile image = dto.getProductImage();
        Product product = null;
        try {
            product = productRepository.save(dto.toEntity());
            byte[] bytes = image.getBytes();
            Path path = Paths.get("/Users/keemzleun/study/tmpimg/",
                    product.getId() + "_" + image.getOriginalFilename());
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            product.updateImagePath(path.toString());   // 더티 체킹. 변경 감기
        } catch (IOException e) {
            // 예외를 터뜨려줘야지 잡아버리면 안됨
            throw new RuntimeException("이미지 저장 실패");
        }
        return product;
    }
    public Product awsCreateProduct(ProductSaveReqDto dto) {
        MultipartFile image = dto.getProductImage();
        Product product = null;
        try {
            product = productRepository.save(dto.toEntity());
            byte[] bytes = image.getBytes();
            Path path = Paths.get("/Users/keemzleun/study/tmpimg/",
                    product.getId() + "_" + image.getOriginalFilename());
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            product.updateImagePath(path.toString());   // 더티 체킹. 변경 감기
        } catch (IOException e) {
            // 예외를 터뜨려줘야지 잡아버리면 안됨
            throw new RuntimeException("이미지 저장 실패");
        }
        return product;

    }
    public Page<ProductResDto> productList(Pageable pageable){
        Page<Product> pages = productRepository.findAll(pageable);
        Page<ProductResDto> dtos = pages.map(a -> a.fromEntity());
        return dtos;
    }
}
