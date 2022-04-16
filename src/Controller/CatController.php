<?php

namespace App\Controller;

use App\Entity\Categorie2eq;
use App\Entity\Equipement;
use App\Entity\Images;
use App\Form\CatType;
use App\Form\EquipementType;
use App\Repository\Categorie2eqRepository;
use App\Repository\EquipementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

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
     * @Route("/listcat_1_json",name="listcat_1_json")
     */
    public function list1_json(NormalizerInterface $normalizer)
    {
        $cat = $this->getDoctrine()->getRepository(Categorie2eq::class)->findAll();
        $jasonContent = $normalizer->normalize($cat,'jason',['groups'=>'post:read']);
        return new Response(json_encode($jasonContent)); }

    /**
     * @Route("/listcat_2",name="listcat_2")
     */
    public function list2()
    {
        $cat = $this->getDoctrine()->getRepository(Categorie2eq::class)->findAll();
        return $this->render("Product/index.html.twig",array('tabclass'=>$cat));
    }

    /**
     * @Route("/add_cat",name="addcat")
     */
    public function add(Request $request)
    {
        $cat = new Categorie2eq();
        $form = $this->createForm(CatType::class,$cat);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() ) {

            $images = $form->get('image')->getData();
            $fichier = md5(uniqid()) . '.' . $images->guessExtension();
            $images->move(
                $this->getParameter('upload_directory'),
                $fichier);
            $cat->setImage($fichier);


            $em = $this->getDoctrine()->getManager();
            $em->persist($cat);
            $em->flush();
            return $this->redirectToRoute("listcat_1");

        }

        return $this->render("cat/add_cat.html.twig",array("formcat"=>$form->createView()));

    }

    /**
     * @Route("/addcat_jason",name="addpcat_jason")
     */
    public function addcat_jason(Request $request,NormalizerInterface $normalizer)
    {
        $em = $this->getDoctrine()->getManager();

        $cat = new Categorie2eq();

        $cat->setName($request->get('name'));
        $cat->setImage($request->get('image'));


        $em->persist($cat);
        $em->flush();
        $jasonContent = $normalizer->normalize($cat,'jason',['groups'=>'post:read']);
        return new Response(json_encode($jasonContent));

    }

    /**
     * @Route("/update_cat/{id}",name="updatecat")
     */
    public function update(Categorie2eqRepository $repository, $id, Request $request){
        $cat= $repository->find($id);
        $form=$this->createForm(CatType::class,$cat);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $images = $form->get('image')->getData();
            $fichier = md5(uniqid()) . '.' . $images->guessExtension();
            $images->move(
                $this->getParameter('upload_directory'),
                $fichier);
            $cat->setImage($fichier);

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("listcat_1");}
        return  $this->render("cat/update_cat.html.twig",
            [
                "formcat"=>$form->createView()

            ]);

    }

    /**
     * @Route("/updatecat_json/{id}",name="updatecat_json")
     */
    public function updatecat_json(Categorie2eqRepository $repository,$id,Request $request,NormalizerInterface $normalizer){
        $cat = $repository->find($id);
        $cat->setName($request->get('name'));
        $cat->setImage($request->get('image'));

        $em=$this->getDoctrine()->getManager();
        $em->flush();
        $jasonContent = $normalizer->normalize($cat,'jason',['groups'=>'post:read']);
        return new Response("Information updated successfully".json_encode($jasonContent));

    }

    /**
     * @Route("/remove_cat/{id}",name="removecat")
     */
    public function delete($id){
        $cat= $this->getDoctrine()->getRepository(Categorie2eq::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($cat);
        $em->flush();
        return $this->redirectToRoute("listcat_1");
    }

    /**
     * @Route("/remove_cat_json_cat_2/{id}",name="removecat")
     */
    public function delete_jason_cat2($id,NormalizerInterface $normalizer){
        $cat= $this->getDoctrine()->getRepository(Categorie2eq::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($cat);
        $em->flush();
        $jasonContent = $normalizer->normalize($cat,'jason',['groups'=>'post:read']);
        return new Response(json_encode($jasonContent));
    }
}
