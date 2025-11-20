package com.ms_backend.mil_sabores_backend;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ms_backend.mil_sabores_backend.model.Producto;
import com.ms_backend.mil_sabores_backend.service.ProductoService;

@Component
public class DataLoader implements CommandLineRunner {
    private final ProductoService productoService;

    public DataLoader(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productoService.obtenerProductos().isEmpty()) {
            List<Producto> productos = List.of(
                    new Producto(
                            null,
                            "Torta de Tres Leches",
                            "Tortas y Pasteles",
                            "Un bizcocho esponjoso bañado en una mezcla de tres tipos de leche (leche evaporada, crema de leche y leche condensada), usualmente cubierto con merengue o crema batida.",
                            22990.0,
                            "Harina, Huevos, Azúcar, Leche Evaporada, Leche Condensada, Crema de Leche, Vainilla",
                            "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEh2FNGpdb9xllndHQkGvVVvkSWs_xoyY4Jmz1gRGQJdi0E-2qAAx71oEUusuBDJHMHly4axFJCYFQcbUOul2U5yoqnkePJy6yEVvUjOvigHNoQ_k2qfgsMDkbwjEaqqxrogLetbKJRA0EkO/s0/torta-tres-leches-menos-azucar.jpg"),
                    new Producto(
                            null,
                            "Pastel de Chocolate (Torta de Chocolate)",
                            "Tortas y Pasteles",
                            "Un clásico pastel hecho con chocolate o cacao en polvo, que puede tener múltiples capas, relleno y cobertura de ganache, buttercream o fudge de chocolate.",
                            24990.0,
                            "Harina, Huevos, Azúcar, Chocolate, Leche Evaporada, Leche Condensada, Crema de Leche, Vainilla",
                            "https://amoradulce.com/wp-content/uploads/2019/12/Torta-chocolate-1_04_13_2024-scaled.jpg"),

                    new Producto(
                            null,
                            "Cheesecake (Tarta de Queso)",
                            "Tortas y Pasteles",
                            "Una tarta dulce con una base de galleta molida y un relleno cremoso hecho principalmente de queso crema, huevos y azúcar. Puede ir horneado o frío, y a menudo se cubre con salsa de frutas.",
                            23990.0,
                            "Galletas, Mantequilla, Queso Crema, Huevos, Azúcar, Crema de Leche, Vainilla, Frutos Rojos",
                            "https://peopleenespanol.com/thmb/HkV2jbNhxgWQi7d0wSMFwEcMQ1w=/750x0/filters:no_upscale():max_bytes(150000):strip_icc()/cheesecake-facil-con-leche-condensada-2000-4160526441114bf3ad8f3409586a2c8a.jpg"),

                    new Producto(
                            null,
                            "Kuchen de Manzana (Tarta de Manzana)",
                            "Tortas y Pasteles",
                            "Una tarta con base de masa y relleno de manzanas cocidas, a menudo condimentadas con canela. Puede tener una cubierta de masa (tipo \"pie\") o una cubierta de migas (streusel).",
                            18990.0,
                            "Masa, Manzanas, Mantequilla, Harina, Azúcar, Canela, Huevo",
                            "https://upload.wikimedia.org/wikipedia/commons/a/a6/Apple_cake_with_vanilla_ice_cream_2.jpg"),

                    new Producto(
                            null,
                            "Selva Negra",
                            "Tortas y Pasteles",
                            "Una torta de capas de bizcocho de chocolate, remojado en kirsch (licor de cereza), con relleno de crema batida y cerezas. Se decora con más crema, virutas de chocolate y cerezas.",
                            26990.0,
                            "Harina, Huevos, Azúcar, Chocolate, Crema de Leche, Cerezas, Kirsch, Vainilla",
                            "https://upload.wikimedia.org/wikipedia/commons/6/66/Black_Forest_gateau.jpg"),

                    new Producto(
                            null,
                            "Pie de Limón (Lemon Pie)",
                            "Tortas y Pasteles",
                            "Una tarta con base de masa, relleno cremoso y ácido de limón (hecho con yemas de huevo y jugo de limón), y una cubierta de merengue dorado.",
                            17990.0,
                            "Masa, Limones, Huevos, Azúcar, Mantequilla, Merengue",
                            "https://www.recetasnestle.cl/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/49d627e69672b6915c22f2eb2dfd1b93.webp?itok=cJEPzpNP"),

                    // Bollería
                    new Producto(
                            null,
                            "Croissant (Medialuna)",
                            "Bollería y Masas Dulces",
                            "Un panecillo de masa de hojaldre leudada, con una textura ligera, aireada y mantecosa. Ideal para el desayuno.",
                            1990.0,
                            "Harina, Mantequilla, Levadura, Azúcar, Sal, Agua",
                            "https://upload.wikimedia.org/wikipedia/commons/d/dc/Croissants_au_beurre_%2818953292873%29.jpg"),

                    new Producto(
                            null,
                            "Napolitana (o Caracola)",
                            "Bollería y Masas Dulces",
                            "Una pieza de bollería hecha con masa de croissant, a menudo rellena de crema pastelera y pasas, o de chocolate.",
                            2290.0,
                            "Harina, Mantequilla, Levadura, Azúcar, Sal, Agua, Crema Pastelera o Chocolate",
                            "https://upload.wikimedia.org/wikipedia/commons/5/5f/Pain_au_chocolat_Luc_Viatour.jpg"),

                    new Producto(
                            null,
                            "Donas (Rosquillas)",
                            "Bollería y Masas Dulces",
                            "Una masa dulce frita, generalmente en forma de anillo o redonda y rellena. Pueden ir glaseadas, cubiertas de azúcar o rellenas de mermelada o crema.",
                            1590.0,
                            "Harina, Azúcar, Levadura, Huevos, Leche, Mantequilla, Aceite para Freír",
                            "https://upload.wikimedia.org/wikipedia/commons/e/ea/Chocolate_donuts_2.jpg"),

                    new Producto(
                            null,
                            "Roles de Canela (Cinnamon Rolls)",
                            "Bollería y Masas Dulces",
                            "Un rollo de masa dulce (tipo brioche) relleno de una mezcla de canela y azúcar (a menudo con mantequilla) y cubierto con un glaseado de queso crema o azúcar.",
                            2990.0,
                            "Harina, Azúcar, Levadura, Huevos, Leche, Mantequilla, Canela, Azúcar Morena",
                            "https://i0.wp.com/sarasellos.com/wp-content/uploads/2024/05/cinnamon-rolls-rollos-canela-3.jpg?resize=1024%2C1024&ssl=1"),

                    new Producto(
                            null,
                            "Palmera",
                            "Bollería y Masas Dulces",
                            "Una galleta o bollo de masa de hojaldre caramelizada, con una característica forma de corazón o palmera.",
                            1890.0,
                            "Harina, Mantequilla, Azúcar, Sal, Agua",
                            "https://imag.bonviveur.com/palmeritas-de-hojaldre.webp"),

                    new Producto(
                            null,
                            "Muffin",
                            "Bollería y Masas Dulces",
                            "Un pequeño panecillo dulce similar a un queque o magdalena, pero más denso. Popular en variedades como arándanos, chocolate o plátano.",
                            2190.0,
                            "Harina, Azúcar, Huevos, Leche, Mantequilla, Polvo de Hornear, Vainilla",
                            "https://www.vitamix.com/content/dam/vitamix/migration/media/recipe/rcppumpkinmuffins/images/pumpkinmuffinsmainjpg.jpg"),

                    // Panes
                    new Producto(
                            null,
                            "Pan de Masa Madre (Sourdough)",
                            "Panes Especiales",
                            "Un pan hecho mediante una fermentación lenta usando un cultivo vivo de levaduras y lactobacilos (masa madre), lo que le da una corteza crujiente y una miga alveolada y ligeramente ácida.",
                            4490.0,
                            "Harina, Agua, Sal, Masa Madre",
                            "https://upload.wikimedia.org/wikipedia/commons/3/3b/Home_made_sour_dough_bread.jpg"),

                    new Producto(
                            null,
                            "Baguette",
                            "Panes Especiales",
                            "Una barra de pan larga y delgada, de origen francés, conocida por su corteza crujiente y su miga suave y aireada.",
                            1590.0,
                            "Harina, Agua, Levadura, Sal",
                            "https://assets.tmecosys.com/image/upload/t_web_rdp_recipe_584x480/img/recipe/ras/Assets/f9f9282c-f4ab-480f-a42d-4347d51dfee2/Derivates/ae113aa0-b9c5-463c-b1e5-316f0390fb74.jpg"),

                    new Producto(
                            null,
                            "Brioche",
                            "Panes Especiales",
                            "Un pan dulce de origen francés, enriquecido con una alta proporción de huevos y mantequilla, lo que le da una miga suave, tierna y de color amarillo.",
                            3990.0,
                            "Harina, Huevos, Mantequilla, Azúcar, Levadura, Leche, Sal",
                            "https://www.harinaselecta.cl/img/blog/tabla-de-madera-con-pan-brioche-dulce-blog-selecta.webp"),

                    new Producto(
                            null,
                            "Focaccia",
                            "Panes Especiales",
                            "Un pan plano italiano, horneado y a menudo cubierto con aceite de oliva, sal gruesa, y hierbas como el romero. Puede llevar también aceitunas o tomates cherry.",
                            3490.0,
                            "Harina, Agua, Levadura, Sal, Aceite de Oliva, Hierbas",
                            "https://www.conasi.eu/blog/wp-content/uploads/2022/02/como-hacer-focaccia-desdes-900x563.jpg"),

                    // Galletas y dulces
                    new Producto(
                            null,
                            "Alfajores",
                            "Galletas y Pequeños Dulces",
                            "Dos galletas suaves unidas por un relleno dulce, comúnmente dulce de leche, y a menudo cubiertas de chocolate o azúcar glas (azúcar impalpable).",
                            1990.0,
                            "Harina, Maicena, Mantequilla, Azúcar, Huevos, Dulce de Leche",
                            "https://upload.wikimedia.org/wikipedia/commons/a/ac/Alfajorartes.JPG"),

                    new Producto(
                            null,
                            "Galletas con Chispas de Chocolate",
                            "Galletas y Pequeños Dulces",
                            "La clásica galleta mantecosa cargada de trozos o chispas de chocolate.",
                            1690.0,
                            "Harina, Mantequilla, Azúcar, Huevos, Chispas de Chocolate, Vainilla",
                            "https://mojo.generalmills.com/api/public/content/_pLFRXFETcuXWg_Z0MhZPw_webp_base.webp?v=1c273e93&t=191ddcab8d1c415fa10fa00a14351227"),

                    new Producto(
                            null,
                            "Macarons",
                            "Galletas y Pequeños Dulces",
                            "Un delicado dulce francés hecho a base de clara de huevo, almendra molida y azúcar. Son dos \"conchas\" crujientes por fuera y suaves por dentro, unidas por un relleno de ganache o crema.",
                            1890.0,
                            "Claras de Huevo, Azúcar, Almendra Molida, Crema, Chocolate o Frutas",
                            "https://upload.wikimedia.org/wikipedia/commons/4/49/Des_macarons_de_chez_Bouillet_%28mars_2023%29.jpg"),

                    new Producto(
                            null,
                            "Brownies",
                            "Galletas y Pequeños Dulces",
                            "Un pequeño pastel de chocolate denso y compacto, a menudo con una textura \"fudgy\" (húmeda). Puede llevar nueces o chispas de chocolate.",
                            2490.0,
                            "Chocolate, Mantequilla, Azúcar, Huevos, Harina, Nueces (opcional)",
                            "https://icecreambakery.in/wp-content/uploads/2024/12/Brownie-Recipe-with-Cocoa-Powder.jpg"),

                    new Producto(
                            null,
                            "Merenguitos (Suspiros)",
                            "Galletas y Pequeños Dulces",
                            "Pequeños dulces hechos de claras de huevo batidas con azúcar, horneados a baja temperatura hasta que quedan secos y crujientes.",
                            2990.0,
                            "Claras de Huevo, Azúcar, Vainilla",
                            "https://www.recetasnestle.com.pe/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/f411ec369da32e4b42ed184220d30a85.webp?itok=xH9iySeF"),

                    new Producto(
                            null,
                            "Torta Pastelera",
                            "Tortas y Pasteles",
                            "Torta rellena de crema pastelera y cubierta con nueces frescas o glaseado.",
                            8990.0,
                            "Harina, Huevos, Azúcar, Leche, Vainilla, Crema pastelera, Nueces",
                            "https://i5.walmartimages.cl/asr/a1afe955-13cc-4f6b-94b2-8e608ad77570.0cbab0a9265430beeaf5a5598a443337.jpeg?null=&odnHeight=612&odnWidth=612&odnBg=FFFFFF"));

            // Save all productos
            productoService.crearProductos(productos);
        }
    }
}
