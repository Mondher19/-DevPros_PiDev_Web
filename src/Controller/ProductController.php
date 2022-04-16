<?php

namespace App\Controller;

use App\Entity\Categorie2eq;
use App\Entity\Equipement;
use App\Form\EquipementType;
use App\Repository\Categorie2eqRepository;
use App\Repository\EquipementRepository;
use phpDocumentor\Reflection\Types\Integer;
use PhpParser\Node\Scalar\String_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ProductController extends AbstractController
{


    /**
     * @Route("/listproduct",name="listProduct")
     */
    public function list()
    {
        $product= $this->getDoctrine()->getRepository(Equipement::class)->findAll();

        return $this->render("Product/admin.html.twig", array('tabclass'=>$product));
    }

    /* jason list*/
    /* jason list*/
    /* jason list*/


    /**
     * @Route("/listproduct_jason",name="listProduct_jason")
     */
    public function list_jason(NormalizerInterface $normalizer)
    {
        $product= $this->getDoctrine()->getRepository(Equipement::class)->findAll();
        $jasonContent = $normalizer->normalize($product,'jason',['groups'=>'post:read']);

        return new Response(json_encode($jasonContent));
    }

    /**
     * @Route("/highPriceMobile",name="listProduct1_jason")
     */
    public function highPriceMobile(NormalizerInterface $normalizer,EquipementRepository  $repository)
    {

        $product= $repository->highPriceMobile();
        $jasonContent = $normalizer->normalize($product,'jason',['groups'=>'post:read']);

        return new Response(json_encode($jasonContent));
    }

    /**
     * @Route("/lowPriceMobile",name="listProduct2_jason")
     */
    public function lowPriceMobile(NormalizerInterface $normalizer,EquipementRepository  $repository)
    {

        $product= $repository->lowPriceMobile();
        $jasonContent = $normalizer->normalize($product,'jason',['groups'=>'post:read']);

        return new Response(json_encode($jasonContent));
    }


    /**
     * @Route("/listproduct_1/{id}",name="listProduct_1")
     */
    public function list1($id)
    {
        $product= $this->getDoctrine()->getRepository(Equipement::class)->find($id);
        return $this->render("Product/admin.html.twig", array('tabclass'=>$product));
    }

    /**
     * @Route("/listproduct_1_json/{id}",name="listProduct_1_json")
     */
    public function list1_json($id,NormalizerInterface  $normalizer )
    {
        $product= $this->getDoctrine()->getRepository(Equipement::class)->find($id);
        $jasonContent = $normalizer->normalize($product,'jason',['groups'=>'post:read']);
        return new Response(json_encode($jasonContent)); }

    /**
     * @Route("/listequipement_json/{id}",name="listequipement_json")
     */
    public function listequipement(EquipementRepository  $repository,$id,NormalizerInterface $normalizer,Request $request)
    {

        $product= $repository->findEquipement($id);
        $jasonContent = $normalizer->normalize($product,'jason',['groups'=>'post:read']);
        return new Response(json_encode($jasonContent));
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

            $images = $form->get('image')->getData();
            $fichier = md5(uniqid()) . '.' . $images->guessExtension();
            $images->move(
                $this->getParameter('upload_directory'),
                $fichier);
            $equipement->setImage($fichier);
            $em = $this->getDoctrine()->getManager();
            $em->persist($equipement);
            $em->flush();
            return $this->redirectToRoute("listProduct");

        }

        return $this->render("Product/createproduct.html.twig",array("formproduit"=>$form->createView()));

    }

    /**
     * @Route("/add_jason",name="addproduit_jason")
     */
    public function add_jason(Request $request,NormalizerInterface $normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $cat=$request->get('categorie_2');
        $ORP = $this->getDoctrine()->getRepository(Categorie2eq::class)->find($cat);
        $equipement = new Equipement();
        $equipement->setCategorie2eq($ORP);
        $equipement->setName($request->get('name'));
        $equipement->setMarque($request->get('marque'));
        $equipement->setDescription($request->get('description'));
        $equipement->setImage($request->get('image'));
        $equipement->setPrix($request->get('prix'));

        $em->persist($equipement);
        $em->flush();
        $jasonContent = $normalizer->normalize($equipement,'jason',['groups'=>'post:read']);
        return new Response(json_encode($jasonContent));

        }

    /**
     * @Route("/update/{id}",name="updateProduct")
     */
    public function update(EquipementRepository $repository,$id,Request $request){
        $equipement= $repository->find($id);
        $form=$this->createForm(EquipementType::class,$equipement);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $images = $form->get('image')->getData();
            $fichier = md5(uniqid()) . '.' . $images->guessExtension();
            $images->move(
                $this->getParameter('upload_directory'),
                $fichier);
            $equipement->setImage($fichier);

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
     * @Route("/update_json/{id}",name="updateProduct_json")
     */
    public function update_json(EquipementRepository $repository,$id,Request $request,NormalizerInterface $normalizer){

        $cat=$request->get('categorie_2');
        $ORP = $this->getDoctrine()->getRepository(Categorie2eq::class)->find($cat);

        $equipement= $repository->find($id);
        $equipement->setName($request->get('name'));
        $equipement->setMarque($request->get('marque'));
        $equipement->setDescription($request->get('description'));
        $equipement->setImage($request->get('image'));
        $equipement->setPrix($request->get('prix'));
        $em=$this->getDoctrine()->getManager();
        $em->flush();
        $jasonContent = $normalizer->normalize($equipement,'jason',['groups'=>'post:read']);
        return new Response("Information updated successfully".json_encode($jasonContent));

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
     * @Route("/remove_json/{id}",name="removeProduct_json")
     */
    public function delete_json($id ,NormalizerInterface $normalizer){
        $equipement= $this->getDoctrine()->getRepository(Equipement::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($equipement);
        $em->flush();
        $jasonContent = $normalizer->normalize($equipement,'jason',['groups'=>'post:read']);
        return new Response("equipement Supprimer avec succée ".json_encode($jasonContent));
    }





    /**
     * @Route("/listequipement_2/{id}",name="listequipement_2")
     */
    public function listequipement_2(EquipementRepository  $repository, $id)
    {
        $product= $repository->findEquipement_1($id);
        return $this->render("Product/Detail_eq.html.twig", array('tabclass'=>$product));
    }







}
