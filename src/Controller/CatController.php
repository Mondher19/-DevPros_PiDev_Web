<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Equipement;
use App\Form\CatType;
use App\Form\EquipementType;
use App\Repository\CategorieRepository;
use App\Repository\EquipementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CatController extends AbstractController
{
    /**
     * @Route("/cat", name="cat")
     */
    public function index(): Response
    {
        return $this->render('cat/index.html.twig', [
            'controller_name' => 'CatController',
        ]);
    }
    /**
     * @Route("/listcat",name="listcat")
     */
    public function list()
    {
        $cat = $this->getDoctrine()->getRepository(Categorie::class)->findAll();
        return $this->render("cat/list_cat.html.twig",array('tabclass'=>$cat));
    }

    /**
     * @Route("/add_cat",name="addcat")
     */
    public function add(Request $request)
    {
        $cat = new Categorie();
        $form = $this->createForm(CatType::class,$cat);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($cat);
            $em->flush();
            return $this->redirectToRoute("listcat");

        }

        return $this->render("cat/add_cat.html.twig",array("formcat"=>$form->createView()));

    }

    /**
     * @Route("/update_cat/{id}",name="updateCat")
     */
    public function update(CategorieRepository $repository,$id,Request $request){
        $cat= $repository->find($id);
        $form=$this->createForm(CatType::class,$cat);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("listcat");}
        return  $this->render("cat/update_cat.html.twig",
            [
                "formcat"=>$form->createView()

            ]);

    }

    /**
     * @Route("/remove_cat/{id}",name="removecat")
     */
    public function delete($id){
        $cat= $this->getDoctrine()->getRepository(Categorie::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($cat);
        $em->flush();
        return $this->redirectToRoute("listcat");
    }
}
