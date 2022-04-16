<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Equipement;
use App\Entity\Panier;
use App\Form\PanierType;
use App\Repository\EquipementRepository;
use App\Repository\PanierRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class PanierController extends AbstractController
{
    /**
     * @Route("/panier", name="panier_index")
     */
    public function index(SessionInterface $session, EquipementRepository $equipementRepository){
        $panier = $session->get('panier', []);

        $panierInfo = [];

        foreach ($panier as $id => $quantite){
            $panierInfo[] = [
                'Equipement' => $equipementRepository->find($id),
                'quantite' => $quantite
            ];

        }
        $total = 0;
        foreach ($panierInfo as $item){
            $totalItem = $item['Equipement']->getPrix() * $item['quantite'];
            $total+=$totalItem;
        }

        return $this->render('panier/index.html.twig', [
            'items'=>$panierInfo,
            'total'=>$total]);
    }

    /**
     * @Route("/panier/add/{id}", name="panier_add")
     */
    public function add($id, SessionInterface $session){
        $panier = $session->get('panier',[]);
        if(!empty(($panier[$id]))) {
            $panier[$id]++;
        }  else {  $panier[$id] = 1;}

        $session->set('panier', $panier);

        return $this->redirectToRoute("panier_index");

    }
    /**
     * @Route("panier/removes/{id}", name="remove")
     */
    public function removes(Equipement $product, SessionInterface $session)
    {

        $panier = $session->get("panier", []);
        $id = $product->getId();

        if (!empty($panier[$id])) {
            if ($panier[$id] > 1) {
                $panier[$id]--;
            } else {
                unset($panier[$id]);
            }
        }

        $session->set("panier", $panier);

        return $this->redirectToRoute("panier_index");
    }
    /**
     * @Route("/panier/remove/{id}", name="panier_remove")
     */
    public function remove($id, SessionInterface $session){
        $panier = $session->get('panier', []);

        if(!empty($panier[$id])) {
            unset($panier[$id]);
        }

        $session->set('panier',$panier);

        return$this->redirectToRoute("panier_index");
    }
    /**
     * @Route("/panier/command/{total}", name="panier_commande")
     */
    public function GoCommande($total){

        return $this->redirectToRoute("commande_index",
            ['t'=>$total]);
    }
}