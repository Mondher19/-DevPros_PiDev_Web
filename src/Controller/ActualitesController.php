<?php

namespace App\Controller;

use App\Entity\Actualites;
use App\Entity\Categorie;
use App\Form\ActualitesType;
use App\Form\CategorieType;
use PhpParser\Node\Scalar\String_;
use Psr\Log\LoggerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\Mailer;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;

class ActualitesController extends AbstractController
{
    /**
     * @Route("/actualites", name="actualites")
     */
    public function index(): Response
    {
        return $this->render('actualites/index.html.twig', [
            'controller_name' => 'ActualitesController',
        ]);
    }
    /**
     * @Route("/listact",name="listactualites")
     */
    public function list()
    {
        $act=$this->getDoctrine()->getRepository(Actualites::class)->findAll();
        return $this->render("actualites/index.html.twig",array('actualites'=>$act));

    }
    /**
     * @Route("/addact", name="addactualites")
     */
    function addactualites(Request $request,LoggerInterface $logger,MailerInterface $emailer)
    {
        $actualite = new Actualites();
        $formact = $this->createForm(ActualitesType::class, $actualite);
        $formact->handleRequest($request);

        if ($formact->isSubmitted() && $formact->isValid()) {
            $image= $formact['image']->getData();
            try {
                if(!is_dir("images_act")){
                    mkdir("images_act");
                }
                $filename=$image->getFileName();
                move_uploaded_file($image,"images_act/".$image->getFileName());

                rename("images_act/".$image->getFileName() , "images_act/".$actualite->getNom().$actualite->getNom().".".$image->getClientOriginalExtension());

            }
            catch (IOExceptionInterface $e) {
                echo "Erreur Profil existant ou erreur upload image ".$e->getPath();
            }
            $actualite->setImage("images_act/".$actualite->getNom().$actualite->getNom().".".$image->getClientOriginalExtension ());
            $em = $this->getDoctrine()->getManager();
            $em->persist($actualite);
            $em->flush();
          //  $this->sendEmail($emailer,$actualite);
            return $this->redirectToRoute("listactualites");

        }
        return $this->render("actualites/actualites.html.twig", array('form' => $formact->createView()));
    }
    public function get_lastpoint($name)
    {

    }

    /**
     * @Route("/updateact/{id}",name="updateact")
     */
    public function updateactualite(Request $request, $id)
    {
        $actualite = $this->getDoctrine()->getRepository(Actualites::class)->find($id);
        $f=new File($actualite->getImage());
        $actualite->setImage("");
        $formact = $this->createForm(ActualitesType::class,$actualite);
        $formact->handleRequest($request);
        if ($formact->isSubmitted() && $formact->isValid()) {
            $image= $formact['image']->getData();
            try {
                if(!is_dir("images_act")){
                    mkdir("images_act");
                }
                $filename=$image->getFileName();
                move_uploaded_file($image,"images_act/".$image->getFileName());

                rename("images_act/".$image->getFileName() , "images_act/".$actualite->getNom().$actualite->getNom().".".$image->getClientOriginalExtension());

            }
            catch (IOExceptionInterface $e) {
                echo "Erreur Profil existant ou erreur upload image ".$e->getPath();
            }
            $actualite->setImage("images_act/".$actualite->getNom().$actualite->getNom().".".$image->getClientOriginalExtension ());
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("listactualites");

        }
        return $this->render("actualites/actualites.html.twig", array('form' => $formact->createView()));
    }
    /**
     * @Route("/deleteact/{id}",name="deleteact")
     */
    public function deleteact($id)
    {
        $actualite = $this->getDoctrine()->getRepository(Actualites::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($actualite);
        $em->flush();
        return $this->redirectToRoute("listactualites");

    }
    /*public function sendEmail(MailerInterface $mailer,$actualite): Response
    {
        $email = (new Email())
            ->from('projectpidev69@gamil.com')
            ->to('aloui.yassine@esprit.tn')
            //->cc('cc@example.com')
            //->bcc('bcc@example.com')
            //->replyTo('fabien@example.com')
            //->priority(Email::PRIORITY_HIGH)
            ->subject('Time for Symfony Mailer!')
            ->html('actualites/email.html.twig');
        $mailer->send($email);

    }*/
}
