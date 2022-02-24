<?php

namespace App\Entity;

use App\Repository\ActualitesRepository;
use Doctrine\ORM\Mapping as ORM;
use DeepCopy\TypeFilter\Date;


/**
 * @ORM\Entity(repositoryClass=ActualitesRepository::class)
 */
class Actualites
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)

     */

    private $nomact;

    /**
     * @ORM\Column(type="text")

     */

    private $image;

    /**
     * @ORM\Column(type="text")

     */

    private $Description;


   /* /**
     * @ORM\ManyToOne(targetEntity="Categorie", inversedBy="actualites")
     * @ORM\JoinColumn(name="categorie_id", referencedColumnName="id")
     */
   /* private $categorie;*/
    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @return mixed
     */
    public function getNomAct()
    {
        return $this->nomact;
    }



    public function setNomAct(string $nomact): self
    {
        $this->image = $nomact;

        return $this;
    }

  /*  /**
     * @return mixed
     */
   /* public function getCategorie()
    {
        return $this->categorie;
    }*/

  /*  /**
     * @param mixed $categorie
     */
   /* public function setCategorie($categorie): void
    {
        $this->categorie = $categorie;
    }*/

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }





}
