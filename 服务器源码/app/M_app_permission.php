<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class M_app_permission extends Model
{

    protected $table='m_app_permission';
    protected $primaryKey = "id";
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
		'manager_id','app_key','permission'
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
      
    ];
    public $timestamps =true;
}
