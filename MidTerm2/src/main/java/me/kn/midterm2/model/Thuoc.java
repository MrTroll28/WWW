package me.kn.midterm2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THUOC")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Thuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATHUOC")
    private Long maThuoc;

    @Column(name = "TENTHUOC")
    private String tenThuoc;

    @Column(name = "GIA")
    private Double gia;

    @Column(name = "NAMSX")
    private int namSX;

    @ManyToOne
    @JoinColumn(name = "MALOAI")
    private LoaiThuoc loaiThuoc;
}
