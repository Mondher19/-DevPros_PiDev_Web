<?php

namespace App\Controller;

use App\Entity\Equipement;
use App\Form\EquipementType;
use App\Repository\EquipementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ProductController extends AbstractController
{
    /**
     * @Route("/Product", name="Product")
     */
    public function index(): Response
    {
        return $this->render('Product/index.html.twig', [
            'controller_name' => 'ProductController',
        ]);
    }

    /**
     * @Route("/listproduct",name="listProduct")
     */
    public function list()
    {
        $product= $this->getDoctrine()->getRepository(Equipement::class)->findAll();
        return $this->render("Product/admin.html.twig", array('tabclass'=>$product));
    }

    /**
         * @Route("/add",name="addproduit")
     */
    public function add(Request $request)
    {
        $equipement = new Equipement();
        $form = $this->createForm(EquipementType::class,$equipement);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($equipement);
            $em->flush();
            return $this->redirectToRoute("listProduct");

        }

        return $this->render("Product/createproduct.html.twig",array("formproduit"=>$form->createView()));

    }

    /**
     * @Route("/update/{id}",name="updateProduct")
     */
    public function update(EquipementRepository $repository,$id,Request $request){
        $equipement= $repository->find($id);
        $form=$this->createForm(EquipementType::class,$equipement);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("listProduct");
        }
        return  $this->render("Product/updateproduct.html.twig",
        [
            "formproduit"=>$form->createView()

        ]);

    }

    /**
     * @Route("/remove/{id}",name="removeProduct")
     */
    public function delete($id){
        $equipement= $this->getDoctrine()->getRepository(Equipement::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($equipement);
        $em->flush();
        return $this->redirectToRoute("listProduct");
    }

    /**
     * @Route("/listproduct_pcgamer",name="listProduct_pcgamer")
     */
    public function listpcgamer(EquipementRepository  $repository)
    {
        $product= $repository->findEquippcgamer();
        return $this->render("Product/pc_gamer.html.twig", array('tabclass'=>$product));
    }

    /**
     * @Route("/listproduct_console",name="listProduct_console")
     */
    public function listconsole(EquipementRepository  $repository)
    {
        $product= $repository->findEquipconsole();
        return $this->render("Product/console.html.twig", array('tabclass'=>$product));
    }

    /**
         * @Route("/listproduct_accessoire",name="listProduct_accessoire")
     */
    public function listaccessoire(EquipementRepository $repository)
    {
        $product= $repository->findEquipaccessoire();
        return $this->render("Product/accessoire.html.twig", array('tabclass'=>$product));
    }


}
