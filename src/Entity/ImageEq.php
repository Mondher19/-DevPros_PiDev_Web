<?php

namespace App\Entity;

use App\Repository\ImageEqRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ImageEqRepository::class)
 */
class ImageEq
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
    private $name;

    /**
     * @ORM\ManyToOne(targetEntity=equipement::class, inversedBy="imageEqs")
     * @ORM\JoinColumn(nullable=false)
     */
    private $equipement;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getEquipement(): ?equipement
    {
        return $this->equipement;
    }

    public function setEquipement(?equipement $equipement): self
    {
        $this->equipement = $equipement;

        return $this;
    }
}
