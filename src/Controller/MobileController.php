<?php

namespace App\Controller;

use App\Entity\Actualites;
use App\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\HttpFoundation\Request;
class MobileController extends AbstractController
{
    /**
     * @Route("/mobile", name="app_mobile")
     */
    public function index(): Response
    {
        return $this->render('mobile/index.html.twig', [
            'controller_name' => 'MobileController',
        ]);
    }

    /**
     * @Route("/getcategori", name="getcategori")
     */
    public function getallcategoriee(NormalizerInterface $normalizer): Response
    {
        $repository=$this->getDoctrine()->getRepository(Categorie::class);
        $cata=$repository->findAll();
        $jsonContent = $normalizer->normalize($cata,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/getactualite", name="getactualite")
     */
    public function getallactualite(NormalizerInterface $normalizer): Response
    {
        $repository=$this->getDoctrine()->getRepository(Actualites::class);
        $actualite=$repository->findAll();
        $jsonContent = $normalizer->normalize($actualite,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }
    /**
     * @Route("/createactualite",name="createactualitee")
     */
    public function addactualite(Request $request,NormalizerInterface $normalizer)
    {
        $act= new Actualites();
        $act->setImage($request->get("image"));
        $act->setDescription($request->get("Description"));
        $act->setNom($request->get("nom"));
        $em = $this->getDoctrine()->getManager();
        $em->persist($act);
        $em->flush();
        $jsonContent=$normalizer->normalize($act,'json',['groups'=>'read']);
return new Response(json_encode($jsonContent));
    }
    /**
     * @Route("/dactualite",name="dactualitee")
     */
    public function delact(Request $request,NormalizerInterface $normalizer):Response
    {
        $id = $request->get("id");
        $em= $this->getDoctrine()->getManager();
        $T=$em->getRepository(Actualites::class)->find($id);
        $em->remove($T);
        $em->flush();
        $jsonContent = $normalizer->normalize($T,'json',['groups'=>'post:read']);
        return new Response("User deleted Successfully ".json_encode($jsonContent))
            ;    }
    /**
     * @Route("/updateactua",name="updateactua")
     */
    public function update(Request $request,NormalizerInterface $normalizer){
        $id = $request->get("id");
        $act= $this->getDoctrine()->getRepository(Actualites::class)->find($id);
        $act->setImage($request->get("image"));
        $act->setDescription($request->get("Description"));
        $act->setNom($request->get("nom"));
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $jsonContent = $normalizer->normalize($act,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));

    }
    /**
     * @Route("/createcategoriee",name="createcategoriee")
     */
    public function addcategoriee(Request $request,NormalizerInterface $normalizer)
    {
        $cat= new Actualites();
        $cat->setNom($request->get("sujet"));
        $em = $this->getDoctrine()->getManager();
        $em->persist($cat);
        $em->flush();
        $jsonContent=$normalizer->normalize($cat,'json',['groups'=>'read']);
        return new Response(json_encode($jsonContent));
    }
    /**
     * @Route("/dcategoriee",name="dcategorieee")
     */
    public function delcat(Request $request,NormalizerInterface $normalizer):Response
    {
        $id = $request->get("sujet");
        $em= $this->getDoctrine()->getManager();
        $T=$em->getRepository(Actualites::class)->find($id);
        $em->remove($T);
        $em->flush();
        $jsonContent = $normalizer->normalize($T,'json',['groups'=>'post:read']);
        return new Response("User deleted Successfully ".json_encode($jsonContent))
            ;    }
    /**
     * @Route("/updateactua",name="updateactua")
     */
    public function updatecat(Request $request,NormalizerInterface $normalizer){
        $id = $request->get("id");
        $cat= $this->getDoctrine()->getRepository(Actualites::class)->find($id);
        $cat->setImage($request->get("sujet"));
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $jsonContent = $normalizer->normalize($cat,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));

    }
}
